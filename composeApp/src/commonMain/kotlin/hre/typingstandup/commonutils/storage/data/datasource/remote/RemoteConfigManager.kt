package hre.typingstandup.utils.storage.data.datasource.remote

expect class RemoteConfigManager() {
    suspend fun fetchAndActivate(): Boolean
    fun getString(key: String): String
    fun getBoolean(key: String): Boolean
    fun getJSON(key: String): String
}