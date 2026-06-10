package hre.typingstandup.signup.data.remote

import hre.typingstandup.signup.domain.model.SignupData
import hre.typingstandup.signup.domain.model.User

interface SignupRemoteDataSource {
    suspend fun createUser(signupData: SignupData): User
}
