package hre.typingstandup.profile.presentation.state

import org.jetbrains.compose.resources.DrawableResource
import typingstandup.composeapp.generated.resources.Res
import typingstandup.composeapp.generated.resources.avatar_placeholder

data class RecentCommitUi(
    val id: String,
    val title: String,
    val wpm: Int,
    val accuracy: Int,
    val relativeDate: String
)

data class ProfileState(
    val username: String = "",
    val joinedDaysAgo: Int = 0,
    val level: Int = 1,
    val avatar: DrawableResource = Res.drawable.avatar_placeholder,

    val wpm: Int? = null,
    val accuracy: Int? = null,

    val recentCommits: List<RecentCommitUi> = emptyList()
)