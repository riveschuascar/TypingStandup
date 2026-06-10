package hre.typingstandup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.FirebaseApp
import hre.typingstandup.di.getModules
import hre.typingstandup.di.platformModule
import hre.typingstandup.navigation.AppNavHost
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import typingstandup.designsystem.composable.theme.typingStandupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        startKoin {
            androidContext(this@MainActivity)
            modules(getModules() + platformModule)
        }

        setContent {
            typingStandupTheme {
                AppNavHost()
            }
        }
    }
}
