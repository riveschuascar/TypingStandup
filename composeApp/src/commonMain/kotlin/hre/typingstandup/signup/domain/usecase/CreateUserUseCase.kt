package hre.typingstandup.signup.domain.usecase

import hre.typingstandup.signup.domain.model.SignupData
import hre.typingstandup.signup.domain.model.SignupResult
import hre.typingstandup.signup.domain.repository.SignupRepository

class CreateUserUseCase(
    private val repository: SignupRepository
) {
    suspend operator fun invoke(signupData: SignupData): SignupResult {
        return repository.signUp(signupData)
    }
}
