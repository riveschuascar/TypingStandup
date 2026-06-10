package hre.typingstandup.profile.domain.repository

import hre.typingstandup.profile.domain.model.Profile

interface ProfileRepository {
    suspend fun getProfile(): Profile
}
