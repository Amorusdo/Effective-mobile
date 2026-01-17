package com.example.auth_ui._.domain.usecase

import com.example.auth_ui._.domain.repository.AuthRepository


class LogoutUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return authRepository.logout()
    }
}