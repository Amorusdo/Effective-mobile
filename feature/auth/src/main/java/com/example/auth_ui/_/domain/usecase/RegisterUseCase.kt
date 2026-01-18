package com.example.auth_ui._.domain.usecase

import com.example.auth_ui._.domain.model.User
import com.example.auth_ui._.domain.repository.AuthRepository
import com.example.domain.error.DomainError


class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String,
        name: String
    ): Result<User> {
        // Валидация
        if (email.isBlank()) {
            return Result.failure(DomainError.EmailEmpty)
        }

        if (password.isBlank()) {
            return Result.failure(DomainError.PasswordEmpty)
        }

        if (password.length < 6) {
            return Result.failure(DomainError.PasswordTooShort)
        }

        if (password != confirmPassword) {
            return Result.failure(DomainError.PasswordsDoNotMatch)
        }

        if (name.isBlank()) {
            return Result.failure(DomainError.NameEmpty)
        }

        return authRepository.register(email, password, name)
    }
}