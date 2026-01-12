package com.example.auth_ui.presentation.view_model

import androidx.lifecycle.ViewModel
import com.example.ui.EmailValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isLoginEnabled: Boolean = false
)

class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        val isValid =  EmailValidator.isValid(email)
        _uiState.update {
            it.copy(
                email = email,
                isEmailValid = isValid,
                isLoginEnabled = isValid && it.password.isNotEmpty()
            )
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                isLoginEnabled = it.isEmailValid && password.isNotEmpty()
            )
        }
    }

    fun onLoginClick() {
        // Логика входа
    }
}