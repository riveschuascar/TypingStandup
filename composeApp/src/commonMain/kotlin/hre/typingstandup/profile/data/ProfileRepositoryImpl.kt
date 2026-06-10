package hre.typingstandup.profile.data

import hre.typingstandup.profile.data.local.ProfileLocalDataSource
import hre.typingstandup.profile.data.provider.ProfileFakeDataProvider
import hre.typingstandup.profile.domain.model.Profile
import hre.typingstandup.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val localDataSource: ProfileLocalDataSource
) : ProfileRepository {

    override suspend fun getProfile(): Profile {
        return localDataSource.findProfile() ?: ProfileFakeDataProvider.buildDemoProfile()
    }
}
