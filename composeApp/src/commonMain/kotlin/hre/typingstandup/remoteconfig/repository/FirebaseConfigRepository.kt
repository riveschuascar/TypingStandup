package hre.typingstandup.remoteconfig.repository

import hre.typingstandup.commonutils.workers.connectivity.ConnectivityChecker
import hre.typingstandup.commonutils.storage.data.datasource.local.IDataBase
import hre.typingstandup.commonutils.storage.remoteconfig.RemoteConfigManager
import hre.typingstandup.commonutils.storage.domain.repository.IRemoteConfigRepository
import hre.typingstandup.onboard.data.ONBOARDING_CONFIG_KEY

class FirebaseConfigRepository(
    private val remote: RemoteConfigManager,
    private val local: IDataBase,
    private val connectivity: ConnectivityChecker
) : IRemoteConfigRepository {

    private val configKeys = listOf(ONBOARDING_CONFIG_KEY)

    private suspend fun sync() {
        if (!connectivity.isOnline()) return

        if (remote.fetchAndActivate()) {
            configKeys.forEach { key ->
                val value = remote.getString(key)
                local.saveConfig(key, value)
            }
        }
    }

    override suspend fun getOnBoard(): String {
        if (connectivity.isOnline()) {
            sync()
        }
        return local.getConfig(ONBOARDING_CONFIG_KEY)
            ?: remote.getString(ONBOARDING_CONFIG_KEY)
    }
}