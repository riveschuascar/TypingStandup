package hre.typingstandup.signup.data

import hre.typingstandup.signup.data.local.SignupLocalDataSource
import hre.typingstandup.signup.data.remote.SignupRemoteDataSource
import hre.typingstandup.signup.domain.model.SignupData
import hre.typingstandup.signup.domain.model.SignupResult
import hre.typingstandup.signup.domain.repository.SignupRepository

class SignupRepositoryImpl(
    private val remoteDataSource: SignupRemoteDataSource,
    private val localDataSource: SignupLocalDataSource
) : SignupRepository {

    override suspend fun signUp(signupData: SignupData): SignupResult {
        return try {
            val user = remoteDataSource.createUser(signupData)
            localDataSource.saveUser(user)
            SignupResult.Success(user)
        } catch (error: Throwable) {
            SignupResult.Error(error.message ?: "No se pudo completar el registro")
        }
    }

    override suspend fun savePendingSignup(signupData: SignupData) {
        localDataSource.savePendingSignup(signupData)
    }
}
