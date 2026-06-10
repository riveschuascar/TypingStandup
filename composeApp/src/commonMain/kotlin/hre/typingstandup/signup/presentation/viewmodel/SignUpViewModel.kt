package hre.typingstandup.signup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hre.typingstandup.signup.domain.model.SignupData
import hre.typingstandup.signup.domain.model.SignupResult
import hre.typingstandup.signup.domain.usecase.CreateUserUseCase
import hre.typingstandup.signup.domain.usecase.SavePendingSignupUseCase
import hre.typingstandup.signup.domain.usecase.SignupValidationResult
import hre.typingstandup.signup.domain.usecase.ValidateSignupUseCase
import hre.typingstandup.signup.presentation.state.SignUpEffect
import hre.typingstandup.signup.presentation.state.SignUpEvent
import hre.typingstandup.signup.presentation.state.SignUpState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val savePendingSignupUseCase: SavePendingSignupUseCase,
    private val validateSignupUseCase: ValidateSignupUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    private val _effect = Channel<SignUpEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.UsernameChanged -> updateUsername(event.value)
            is SignUpEvent.EmailChanged -> updateEmail(event.value)
            is SignUpEvent.PasswordChanged -> updatePassword(event.value)
            SignUpEvent.SignUpClicked -> signUp()
        }
    }

    private fun updateUsername(value: String) {
        _state.update { it.copy(username = value, error = null) }
        validateForm()
    }

    private fun updateEmail(value: String) {
        _state.update { it.copy(email = value, error = null) }
        validateForm()
    }

    private fun updatePassword(value: String) {
        _state.update { it.copy(password = value, error = null) }
        validateForm()
    }

    private fun validateForm() {
        val current = _state.value

        _state.update {
            it.copy(
                isSignUpEnabled =
                    current.username.isNotBlank() &&
                            current.email.isNotBlank() &&
                            current.password.isNotBlank()
            )
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            val current = _state.value
            val signupData = SignupData(
                username = current.username,
                email = current.email,
                password = current.password
            )

            when (val validationResult = validateSignupUseCase(signupData)) {
                is SignupValidationResult.Invalid -> {
                    _state.update { it.copy(error = validationResult.message) }
                    _effect.send(SignUpEffect.ShowError(validationResult.message))
                    return@launch
                }
                SignupValidationResult.Valid -> Unit
            }

            _state.update { it.copy(isLoading = true, error = null) }

            try {
                savePendingSignupUseCase(signupData)
                val result = createUserUseCase(signupData)

                when (result) {
                    is SignupResult.Success -> {
                        _effect.send(SignUpEffect.NavigateToHome)
                    }
                    is SignupResult.Error -> {
                        _state.update { it.copy(error = result.message) }
                        _effect.send(SignUpEffect.ShowError(result.message))
                    }
                }
            } catch (e: Exception) {
                val message = e.message ?: "Unknown error"
                _state.update { it.copy(error = message) }
                _effect.send(SignUpEffect.ShowError(message))
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}