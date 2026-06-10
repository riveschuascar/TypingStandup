package hre.typingstandup.signup.domain.usecase

import hre.typingstandup.signup.domain.model.SignupData

sealed class SignupValidationResult {
    object Valid : SignupValidationResult()
    data class Invalid(val message: String) : SignupValidationResult()
}

class ValidateSignupUseCase {
    operator fun invoke(data: SignupData): SignupValidationResult {
        if (data.username.isBlank()) {
            return SignupValidationResult.Invalid("El nombre de usuario no puede estar vacío")
        }
        if (data.email.isBlank()) {
            return SignupValidationResult.Invalid("El correo no puede estar vacío")
        }
        if (!data.email.contains("@") || !data.email.contains(".")) {
            return SignupValidationResult.Invalid("Ingrese un correo válido")
        }
        if (data.password.length < 6) {
            return SignupValidationResult.Invalid("La contraseña debe tener al menos 6 caracteres")
        }
        return SignupValidationResult.Valid
    }
}
