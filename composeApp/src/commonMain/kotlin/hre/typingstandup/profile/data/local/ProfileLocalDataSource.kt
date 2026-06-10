package hre.typingstandup.profile.data.local

import hre.typingstandup.profile.domain.model.Profile

interface ProfileLocalDataSource {
    suspend fun findProfile(): Profile?
}
