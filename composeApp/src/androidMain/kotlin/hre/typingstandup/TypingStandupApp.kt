package hre.typingstandup

import android.app.Application
import hre.typingstandup.di.getModules
import hre.typingstandup.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TypingStandupApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TypingStandupApp)
            modules(getModules() + platformModule)
        }
    }
}
