package hre.typingstandup.signup.domain.repository

import hre.typingstandup.signup.domain.model.SignupData
import hre.typingstandup.signup.domain.model.SignupResult

interface SignupRepository {
    suspend fun signUp(signupData: SignupData): SignupResult
    suspend fun savePendingSignup(signupData: SignupData)
}
