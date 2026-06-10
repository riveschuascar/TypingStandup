package hre.typingstandup.signup.domain.usecase

import hre.typingstandup.signup.domain.model.SignupData
import hre.typingstandup.signup.domain.repository.SignupRepository

class SavePendingSignupUseCase(
    private val repository: SignupRepository
) {
    suspend operator fun invoke(signupData: SignupData) {
        repository.savePendingSignup(signupData)
    }
}
