package hre.typingstandup.profile.data.local

import hre.typingstandup.commonutils.storage.database.AppDatabase
import hre.typingstandup.profile.data.provider.ProfileFakeDataProvider
import hre.typingstandup.profile.domain.model.Profile

class ProfileLocalDataSourceAndroid(
    private val appDatabase: AppDatabase
) : ProfileLocalDataSource {

    private val userDao = appDatabase.userDao()

    override suspend fun findProfile(): Profile? {
        val userEntity = userDao.findFirstUser() ?: return null
        return ProfileFakeDataProvider.buildProfileForUser(
            username = userEntity.username,
            email = userEntity.email
        )
    }
}
