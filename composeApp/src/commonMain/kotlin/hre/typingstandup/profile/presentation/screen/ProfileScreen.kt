package hre.typingstandup.profile.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hre.typingstandup.profile.presentation.state.ProfileState
import hre.typingstandup.profile.presentation.state.RecentCommitUi
import typingstandup.designsystem.composable.ui.BotNavBar
import typingstandup.designsystem.composable.ui.TopBarVariant
import typingstandup.designsystem.composable.ui.TypingStandupTopBar

@Composable
fun ProfileScreen(
    state: ProfileState
) {
    Scaffold(
        topBar = {
            TypingStandupTopBar(
                variant = TopBarVariant.Compact
            )
        },
        bottomBar = {
            BotNavBar(
                selectedIndex = 3,
                onItemClick = {}
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

@Composable
fun RecentCommitsSection(commits: List<RecentCommitUi>) {
    TODO("Not yet implemented")
}

@Composable
fun StatsSection(wpm: Int?, accuracy: Int?) {
    TODO("Not yet implemented")
}

@Composable
fun ProfileHeader(x0: ProfileState) {
    TODO("Not yet implemented")
}