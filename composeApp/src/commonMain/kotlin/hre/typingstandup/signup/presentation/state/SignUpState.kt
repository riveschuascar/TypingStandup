package hre.typingstandup.signup.presentation.state

data class SignUpState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSignUpEnabled: Boolean = false,
    val error: String? = null
)