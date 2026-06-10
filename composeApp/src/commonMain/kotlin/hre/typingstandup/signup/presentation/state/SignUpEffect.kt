package hre.typingstandup.signup.presentation.state

sealed interface SignUpEffect {
    data class ShowError(
        val message: String
    ) : SignUpEffect

    data object NavigateToHome : SignUpEffect
}