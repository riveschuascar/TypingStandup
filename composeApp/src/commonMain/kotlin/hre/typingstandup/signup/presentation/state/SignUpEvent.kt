package hre.typingstandup.signup.presentation.state

sealed interface SignUpEvent {
    data class UsernameChanged(
        val value: String
    ) : SignUpEvent

    data class EmailChanged(
        val value: String
    ) : SignUpEvent

    data class PasswordChanged(
        val value: String
    ) : SignUpEvent

    data object SignUpClicked : SignUpEvent
}