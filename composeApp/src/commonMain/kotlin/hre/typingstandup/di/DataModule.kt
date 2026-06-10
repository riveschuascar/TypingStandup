package hre.typingstandup.di

import hre.typingstandup.commonutils.storage.data.datasource.local.IDataBase
import hre.typingstandup.commonutils.storage.remoteconfig.RemoteConfigManager
import hre.typingstandup.commonutils.storage.domain.repository.IRemoteConfigRepository
import hre.typingstandup.commonutils.workers.connectivity.ConnectivityChecker
import hre.typingstandup.remoteconfig.repository.FirebaseConfigRepository
import hre.typingstandup.signup.data.SignupRepositoryImpl
import hre.typingstandup.signup.data.local.SignupLocalDataSource
import hre.typingstandup.signup.data.remote.SignupRemoteDataSource
import hre.typingstandup.signup.domain.repository.SignupRepository
import hre.typingstandup.commonutils.storage.data.datasource.local.InMemoryDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    // Instances
    singleOf(::RemoteConfigManager)
    singleOf(::ConnectivityChecker)
    single<IDataBase> { InMemoryDatabase() }

    // Repositories and data sources
    single<IRemoteConfigRepository> {
        FirebaseConfigRepository(
            remote = get(),
            local = get(),
            connectivity = get()
        )
    }
    single<SignupRepository> {
        SignupRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }
}