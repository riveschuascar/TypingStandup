package hre.typingstandup.signup.domain.model

sealed class SignupResult {
    data class Success(val user: User) : SignupResult()
    data class Error(val message: String) : SignupResult()
}
