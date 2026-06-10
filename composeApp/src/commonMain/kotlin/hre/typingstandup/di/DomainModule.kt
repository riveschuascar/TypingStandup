package hre.typingstandup.di

import hre.typingstandup.commonutils.storage.domain.usecase.GetOnBoardUseCase
import hre.typingstandup.signup.domain.usecase.CreateUserUseCase
import hre.typingstandup.signup.domain.usecase.SavePendingSignupUseCase
import hre.typingstandup.signup.domain.usecase.ValidateSignupUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::CreateUserUseCase)
    singleOf(::ValidateSignupUseCase)
    singleOf(::SavePendingSignupUseCase)
    singleOf(::GetOnBoardUseCase)
}