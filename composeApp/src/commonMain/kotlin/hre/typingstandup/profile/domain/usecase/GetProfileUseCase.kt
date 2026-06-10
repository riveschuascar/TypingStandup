package hre.typingstandup.profile.domain.usecase

import hre.typingstandup.profile.domain.model.Profile
import hre.typingstandup.profile.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): Profile {
        return repository.getProfile()
    }
}
