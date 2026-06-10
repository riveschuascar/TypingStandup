package hre.typingstandup.di

import hre.typingstandup.onboard.presentation.OnboardingViewModel
import hre.typingstandup.signup.presentation.viewmodel.SignUpViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::SignUpViewModel)
}