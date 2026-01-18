package com.example.auth_ui._.domain.usecase

import com.example.auth_ui._.domain.model.User
import com.example.auth_ui._.domain.repository.AuthRepository
import com.example.domain.error.DomainError


class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        // Валидация перед отправкой запроса
        if (email.isBlank()) {
            return Result.failure(DomainError.EmailEmpty)
        }

        if (password.isBlank()) {
            return Result.failure(DomainError.PasswordEmpty)
        }

        if (password.length < 6) {
            return Result.failure(DomainError.PasswordTooShort)
        }

        return authRepository.login(email, password)
    }
}