package com.example.auth_ui._.domain.usecase

import com.example.auth_ui._.domain.model.User
import com.example.auth_ui._.domain.repository.AuthRepository


class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        // Валидация перед отправкой запроса
        if (email.isBlank()) {
            return Result.failure(Exception("Email не может быть пустым"))
        }

        if (password.isBlank()) {
            return Result.failure(Exception("Пароль не может быть пустым"))
        }

        if (password.length < 6) {
            return Result.failure(Exception("Пароль должен содержать минимум 6 символов"))
        }

        return authRepository.login(email, password)
    }
}