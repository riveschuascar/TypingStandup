package hre.typingstandup.signup.presentation.composable

import androidx.compose.runtime.Composable
import hre.typingstandup.signup.presentation.state.SignUpEvent
import hre.typingstandup.signup.presentation.state.SignUpState

@Composable
fun SignUpForm(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit
) {
    SignUpTextField(
        value = state.username,
        label = "USER_NAME",
        onValueChange = {
            onEvent(
                SignUpEvent.UsernameChanged(it)
            )
        }
    )
    SignUpTextField(
        value = state.email,
        label = "EMAIL",
        onValueChange = {
            onEvent(
                SignUpEvent.EmailChanged(it)
            )
        }
    )
    SignUpTextField(
        value = state.password,
        label = "PASS",
        onValueChange = {
            onEvent(
                SignUpEvent.PasswordChanged(it)
            )
        }
    )
}