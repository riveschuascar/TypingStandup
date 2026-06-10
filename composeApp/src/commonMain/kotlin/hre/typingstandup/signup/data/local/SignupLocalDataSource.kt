package hre.typingstandup.signup.data.local

import hre.typingstandup.signup.domain.model.SignupData
import hre.typingstandup.signup.domain.model.User

interface SignupLocalDataSource {
    suspend fun saveUser(user: User)
    suspend fun savePendingSignup(signupData: SignupData)
    suspend fun findUserById(id: String): User?
}
