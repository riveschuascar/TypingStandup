package hre.typingstandup.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hre.typingstandup.onboard.presentation.OnboardingViewModel
import hre.typingstandup.onboard.presentation.screen.OnboardingScreen
import hre.typingstandup.signup.presentation.screen.SignUpScreen
import hre.typingstandup.navigation.HomeScreen
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.getValue


@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Onboarding.route) {
        composable(NavRoute.Onboarding.route) {
            val viewModel: OnboardingViewModel = koinViewModel()
            val state by viewModel.state.collectAsState()

            OnboardingScreen(
                state = state,
                onIntent = viewModel::onIntent,
                onSkip = {
                    navController.navigate(NavRoute.SignUp.route)
                },
                onFinish = {
                    navController.navigate(NavRoute.SignUp.route)
                }
            )
        }

        composable(NavRoute.SignUp.route) {
            SignUpScreen(navController)
        }

        composable(NavRoute.Home.route) {
            HomeScreen()
        }
    }
}
