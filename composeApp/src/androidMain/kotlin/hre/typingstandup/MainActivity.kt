package hre.typingstandup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.FirebaseApp
import hre.typingstandup.onboard.presentation.OnboardingViewModel
import hre.typingstandup.onboard.presentation.screen.OnboardingScreen
import typingstandup.designsystem.composable.theme.typingStandupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        setContent {
            typingStandupTheme {
                val showHome = remember { mutableStateOf(false) }

                val onboardingViewModel: OnboardingViewModel = viewModel()
                val onboardingState by onboardingViewModel.state.collectAsState()

                if (showHome.value) {
                    HomeTemporal()
                } else {
                    OnboardingScreen(
                        state = onboardingState,
                        onIntent = onboardingViewModel::onIntent,
                        onSkip = {
                            showHome.value = true
                        },
                        onFinish = {
                            showHome.value = true
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeTemporal() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("HOME - Aplicación iniciada")
    }
}