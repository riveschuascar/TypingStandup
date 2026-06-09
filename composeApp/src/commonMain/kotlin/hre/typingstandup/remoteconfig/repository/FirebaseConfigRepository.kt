package hre.typingstandup.remoteconfig.repository

import hre.typingstandup.utils.workers.connectivity.ConnectivityChecker
import hre.typingstandup.utils.storage.data.datasource.local.IDataBase
import hre.typingstandup.utils.storage.data.datasource.remote.RemoteConfigManager
import hre.typingstandup.utils.storage.domain.repository.IRemoteConfigRepository

class FirebaseConfigRepository(
    private val remote: RemoteConfigManager,
    private val local: IDataBase,
    private val connectivity: ConnectivityChecker
) : IRemoteConfigRepository {

    private val configKeys = listOf("onboard_config")

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
        return local.getConfig("greeting_text")
            ?: remote.getString("greeting_text")
    }
}