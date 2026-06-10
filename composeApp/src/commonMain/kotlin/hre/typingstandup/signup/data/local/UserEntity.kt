package hre.typingstandup.signup.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val username: String,
    val email: String,
    val pending: Boolean = false
)

fun UserEntity.toDomain() = hre.typingstandup.signup.domain.model.User(
    id = id,
    username = username,
    email = email
)

fun hre.typingstandup.signup.domain.model.User.toEntity(pending: Boolean = false) = UserEntity(
    id = id,
    username = username,
    email = email,
    pending = pending
)

fun hre.typingstandup.signup.domain.model.SignupData.toPendingEntity() = UserEntity(
    id = username.lowercase(),
    username = username,
    email = email,
    pending = true
)
