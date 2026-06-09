package hre.typingstandup.commonutils.storage.domain.repository

interface IRemoteConfigRepository {
    /**
     * Gets the config of the OnBoard screen
     * @return JSON string
     */
    suspend fun getOnBoard(): String
}