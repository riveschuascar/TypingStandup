package hre.typingstandup.onboard.domain.usecase

import hre.typingstandup.onboard.domain.model.OnboardingSlide
import hre.typingstandup.onboard.domain.repository.OnboardingRepository

class GetOnboardingSlidesUseCase(
    private val repository: OnboardingRepository
) {
    suspend operator fun invoke(): List<OnboardingSlide> {
        return repository.getSlides().sortedBy { it.id }
    }
}