package hre.typingstandup.commonutils.storage.data.datasource.local

class InMemoryDatabase : IDataBase {
    private val storage = mutableMapOf<String, String>()

    override suspend fun saveConfig(key: String, value: String) {
        storage[key] = value
    }

    override suspend fun getConfig(key: String): String? {
        return storage[key]
    }

    override suspend fun hasConfig(key: String): Boolean {
        return storage.containsKey(key)
    }
}
