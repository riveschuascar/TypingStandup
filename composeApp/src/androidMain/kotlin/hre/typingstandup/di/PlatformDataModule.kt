package hre.typingstandup.di

import hre.typingstandup.commonutils.storage.database.AppDatabase
import hre.typingstandup.commonutils.storage.database.getDatabaseBuilder
import hre.typingstandup.profile.data.local.ProfileLocalDataSource
import hre.typingstandup.profile.data.local.ProfileLocalDataSourceAndroid
import hre.typingstandup.signup.data.local.SignupLocalDataSource
import hre.typingstandup.signup.data.local.SignupLocalDataSourceAndroid
import hre.typingstandup.signup.data.remote.SignupRemoteDataSource
import hre.typingstandup.signup.data.remote.SignupRemoteDataSourceAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val platformModule = module {
    single<AppDatabase> {
        getDatabaseBuilder(androidContext()).build()
    }

    single<SignupLocalDataSource> {
        SignupLocalDataSourceAndroid(get())
    }

    single<SignupRemoteDataSource> {
        SignupRemoteDataSourceAndroid()
    }

    single<ProfileLocalDataSource> {
        ProfileLocalDataSourceAndroid(get())
    }
}
