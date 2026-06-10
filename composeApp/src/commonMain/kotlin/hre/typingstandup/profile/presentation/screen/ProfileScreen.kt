package hre.typingstandup.profile.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import hre.typingstandup.navigation.NavRoute
import hre.typingstandup.profile.presentation.composable.ProfileHeader
import hre.typingstandup.profile.presentation.composable.RecentCommitsSection
import hre.typingstandup.profile.presentation.composable.StatsSection
import hre.typingstandup.profile.presentation.state.ProfileState
import hre.typingstandup.profile.presentation.viewmodel.ProfileViewModel
import typingstandup.designsystem.composable.ui.BotNavBar
import typingstandup.designsystem.composable.ui.TopBarVariant
import typingstandup.designsystem.composable.ui.TypingStandupTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TypingStandupTopBar(
                variant = TopBarVariant.Compact
            )
        },
        bottomBar = {
            BotNavBar(
                selectedIndex = 3,
                onItemClick = { index ->
                    if (index == 0) {
                        navController.navigate(NavRoute.Home.route) {
                            popUpTo(NavRoute.Home.route)
                        }
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            item {
                ProfileHeader(state)
            }

            item {
                StatsSection(
                    wpm = state.wpm,
                    accuracy = state.accuracy
                )
            }

            item {
                RecentCommitsSection(
                    commits = state.recentCommits
                )
            }
        }
    }
}
