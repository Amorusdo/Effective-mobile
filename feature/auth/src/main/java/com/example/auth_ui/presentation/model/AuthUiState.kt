package com.example.auth_ui.presentation.model

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isLoginEnabled: Boolean = false
)