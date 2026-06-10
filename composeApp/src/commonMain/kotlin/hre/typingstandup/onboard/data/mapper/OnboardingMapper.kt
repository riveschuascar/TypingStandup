package hre.typingstandup.onboard.data.mapper

import hre.typingstandup.onboard.domain.model.OnboardingSlide
import hre.typingstandup.onboard.presentation.screen.OnboardingSlideUi

fun OnboardingSlide.toUi(): OnboardingSlideUi {
    return OnboardingSlideUi(id, title, description, imageUrl)
}

fun OnboardingSlideUi.toDomain(): OnboardingSlide {
    return OnboardingSlide(id, title, description, imageUrl)
}