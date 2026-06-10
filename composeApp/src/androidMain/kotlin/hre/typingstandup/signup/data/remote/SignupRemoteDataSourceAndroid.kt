package hre.typingstandup.signup.data.remote

import com.google.firebase.database.database
import com.google.firebase.Firebase
import hre.typingstandup.signup.domain.model.SignupData
import hre.typingstandup.signup.domain.model.User
import kotlinx.coroutines.tasks.await

class SignupRemoteDataSourceAndroid : SignupRemoteDataSource {

    private val usersReference = Firebase.database.reference.child("signups")

    override suspend fun createUser(signupData: SignupData): User {
        val newRef = usersReference.push()
        val id = newRef.key.orEmpty().takeIf { it.isNotBlank() }
            ?: throw IllegalStateException("No se pudo generar el ID de usuario")

        val payload = mapOf(
            "id" to id,
            "username" to signupData.username,
            "email" to signupData.email
        )

        newRef.setValue(payload).await()

        return User(
            id = id,
            username = signupData.username,
            email = signupData.email
        )
    }
}
