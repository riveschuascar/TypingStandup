package hre.typingstandup.signup.data.local

import hre.typingstandup.commonutils.storage.database.AppDatabase
import hre.typingstandup.signup.domain.model.SignupData
import hre.typingstandup.signup.domain.model.User

class SignupLocalDataSourceAndroid(
    private val appDatabase: AppDatabase
) : SignupLocalDataSource {

    private val userDao = appDatabase.userDao()

    override suspend fun saveUser(user: User) {
        userDao.insert(user.toEntity())
    }

    override suspend fun savePendingSignup(signupData: SignupData) {
        val pendingUser = signupData.toPendingEntity()
        userDao.insert(pendingUser)
    }

    override suspend fun findUserById(id: String): User? {
        return userDao.findById(id)?.toDomain()
    }
}
