package hre.typingstandup.signup.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import hre.typingstandup.navigation.NavRoute
import hre.typingstandup.signup.presentation.composable.SignUpForm
import hre.typingstandup.signup.presentation.state.SignUpEffect
import hre.typingstandup.signup.presentation.state.SignUpEvent
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
                    navController.navigate(route = NavRoute.Home.route) {
                        popUpTo(NavRoute.Onboarding.route) { inclusive = true }
                    }
                }
                is SignUpEffect.ShowError -> {
                    // Handle error (e.g., show Snackbar)
                }
            }
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Create Account")
            
            Spacer(modifier = Modifier.height(16.dp))

            SignUpForm(
                state = state,
                onEvent = viewModel::onEvent
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.onEvent(SignUpEvent.SignUpClicked) },
                enabled = state.isSignUpEnabled && !state.isLoading
            ) {
                Text(if (state.isLoading) "Loading..." else "Sign Up")
            }
        }
    }
}
