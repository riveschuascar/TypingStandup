package hre.typingstandup.signup.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import hre.typingstandup.navigation.NavRoute
import hre.typingstandup.signup.presentation.state.SignUpEffect
import hre.typingstandup.signup.presentation.viewmodel.SignUpViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SignUpEffect.NavigateToHome -> {
                    navController.navigate(route = NavRoute.Home.route)
                }

                is SignUpEffect.ShowError -> TODO()
            }
        }
    }
}
