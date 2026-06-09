package hre.typingstandup.utils.storage.data.datasource.remote

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import kotlinx.coroutines.tasks.await


actual class RemoteConfigManager {
    private val remote: FirebaseRemoteConfig = Firebase.remoteConfig

    init {
        val configSettings = com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(30)
            .build()

        remote.setConfigSettingsAsync(configSettings)

        remote.setDefaultsAsync(
            mapOf(
                "feature_enabled" to false,
                "welcome_text" to "Hola default"
            )
        )
    }

    actual suspend fun fetchAndActivate(): Boolean {
        return remote.fetchAndActivate().await()
    }

    actual fun getString(key: String): String {
        remote.fetchAndActivate()
        return remote.getString(key)
    }

    actual fun getBoolean(key: String): Boolean {
        remote.fetchAndActivate()
        return remote.getBoolean(key)
    }

    actual fun getJSON(key: String): String {
        remote.fetchAndActivate()
        return remote.getString(key)
    }
}