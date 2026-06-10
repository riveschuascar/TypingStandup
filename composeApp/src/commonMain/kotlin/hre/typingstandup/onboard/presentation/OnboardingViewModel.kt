package hre.typingstandup.onboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hre.typingstandup.commonutils.storage.domain.usecase.GetOnBoardUseCase
import hre.typingstandup.onboard.data.defaultOnboardingSlides
import hre.typingstandup.onboard.data.parseOnboardingJson
import hre.typingstandup.onboard.presentation.screen.OnboardingSlideUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OnboardingState(
    val isLoading: Boolean = true,
    val currentPage: Int = 0,
    val selectedMode: String = "CLASICO",
    val slides: List<OnboardingSlideUi> = defaultOnboardingSlides()
)

sealed interface OnboardingIntent {
    data object NextPage : OnboardingIntent
    data object PreviousPage : OnboardingIntent
    data class SelectMode(val mode: String) : OnboardingIntent
}

class OnboardingViewModel(
    private val getOnBoardUseCase: GetOnBoardUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    init {
        loadOnboarding()
    }

    private fun loadOnboarding() {
        viewModelScope.launch {
            runCatching {
                val json = getOnBoardUseCase()
                parseOnboardingJson(json)
            }.onSuccess { slides ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        slides = if (slides.isNotEmpty()) slides else defaultOnboardingSlides()
                    )
                }
            }.onFailure {
                _state.update {
                    it.copy(
                        isLoading = false,
                        slides = defaultOnboardingSlides()
                    )
                }
            }
        }
    }

    fun onIntent(intent: OnboardingIntent) {
        when (intent) {
            OnboardingIntent.NextPage -> nextPage()
            OnboardingIntent.PreviousPage -> previousPage()
            is OnboardingIntent.SelectMode -> {
                _state.update { it.copy(selectedMode = intent.mode) }
            }
        }
    }

    private fun nextPage() {
        _state.update {
            it.copy(currentPage = (it.currentPage + 1).coerceAtMost(it.slides.lastIndex))
        }
    }

    private fun previousPage() {
        _state.update {
            it.copy(currentPage = (it.currentPage - 1).coerceAtLeast(0))
        }
    }
}