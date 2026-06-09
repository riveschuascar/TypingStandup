package hre.typingstandup.utils.storage.data.datasource.local

interface IDataBase {
    suspend fun saveConfig(key: String, value: String)
    suspend fun getConfig(key: String): String?
    suspend fun hasConfig(key: String): Boolean
}
