package hre.typingstandup.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hre.typingstandup.profile.domain.usecase.GetProfileUseCase
import hre.typingstandup.profile.presentation.state.ProfileState
import hre.typingstandup.profile.presentation.state.RecentCommitUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            val profile = getProfileUseCase()

            _state.update {
                it.copy(
                    username = profile.username,
                    joinedDaysAgo = profile.joinedDaysAgo,
                    level = profile.level,
                    wpm = profile.wpm,
                    accuracy = profile.accuracy,
                    recentCommits = profile.recentCommits.map { commit ->
                        RecentCommitUi(
                            id = commit.id,
                            title = commit.title,
                            wpm = commit.wpm,
                            accuracy = commit.accuracy,
                            relativeDate = commit.relativeDate
                        )
                    }
                )
            }
        }
    }
}
