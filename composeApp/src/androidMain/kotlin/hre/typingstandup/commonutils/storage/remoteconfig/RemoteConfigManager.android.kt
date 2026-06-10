package hre.typingstandup.commonutils.storage.remoteconfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import hre.typingstandup.onboard.data.DEFAULT_ONBOARDING_JSON
import hre.typingstandup.onboard.data.ONBOARDING_CONFIG_KEY
import kotlinx.coroutines.tasks.await

actual class RemoteConfigManager actual constructor() {
    private val remote: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(30)
            .build()

        remote.setConfigSettingsAsync(configSettings)

        remote.setDefaultsAsync(
            mapOf(
                ONBOARDING_CONFIG_KEY to DEFAULT_ONBOARDING_JSON
            )
        )
    }

    actual suspend fun fetchAndActivate(): Boolean {
        return remote.fetchAndActivate().await()
    }

    actual fun getString(key: String): String {
        return remote.getString(key)
    }

    actual fun getBoolean(key: String): Boolean {
        return remote.getBoolean(key)
    }

    actual fun getJSON(key: String): String {
        return remote.getString(key)
    }
}