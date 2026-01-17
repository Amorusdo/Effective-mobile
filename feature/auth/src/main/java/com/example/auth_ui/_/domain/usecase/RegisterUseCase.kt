package com.example.auth_ui._.domain.usecase

import com.example.auth_ui._.domain.model.User
import com.example.auth_ui._.domain.repository.AuthRepository


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
            return Result.failure(Exception("Email не может быть пустым"))
        }

        if (password.isBlank()) {
            return Result.failure(Exception("Пароль не может быть пустым"))
        }

        if (password.length < 6) {
            return Result.failure(Exception("Пароль должен содержать минимум 6 символов"))
        }

        if (password != confirmPassword) {
            return Result.failure(Exception("Пароли не совпадают"))
        }

        if (name.isBlank()) {
            return Result.failure(Exception("Имя не может быть пустым"))
        }

        return authRepository.register(email, password, name)
    }
}