package hre.typingstandup.onboard.domain.repository

import hre.typingstandup.onboard.domain.model.OnboardingSlide

interface OnboardingRepository {
    suspend fun getSlides(): List<OnboardingSlide>
}