package hre.typingstandup.signup.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    suspend fun findById(id: String): UserEntity?

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun findFirstUser(): UserEntity?
}
