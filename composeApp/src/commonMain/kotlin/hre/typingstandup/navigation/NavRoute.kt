package hre.typingstandup.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute(val route: String) {
    @Serializable
    object Onboarding : NavRoute("onboarding")

    @Serializable
    object SignUp : NavRoute("sign_up")

    @Serializable
    object Home : NavRoute("home")

    @Serializable
    object Profile : NavRoute("profile")
}
