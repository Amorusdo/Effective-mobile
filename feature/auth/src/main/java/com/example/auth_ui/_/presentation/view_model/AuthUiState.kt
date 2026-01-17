package com.example.auth_ui._.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth_ui._.domain.usecase.LoginUseCase
import com.example.common.utils.EmailValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isLoginEnabled: Boolean = false,
    val isLoading: Boolean = false,
//    val error: String? = null,
//    val user: User? = null
)

class AuthViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        val isValid = EmailValidator.isValid(email)
        _uiState.update {
            it.copy(
                email = email,
                isEmailValid = isValid,
               isLoginEnabled = isValid && it.password.isNotEmpty()
//                       && !it.isLoading, error = null
            )
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                isLoginEnabled = it.isEmailValid && password.isNotEmpty()
//                        && !it.isLoading, error = null
            )
        }
    }

    fun onLoginClick() {
        viewModelScope.launch {
//            _uiState.update { it.copy(isLoading = true, error = null, isLoginEnabled = false) }

            loginUseCase(
                email = _uiState.value.email,
                password = _uiState.value.password
            )
//                .onSuccess { user ->
//                    _uiState.update {
//                        it.copy(isLoading = false, user = user, error = null
//                        )
//                    }
//                }
//                .onFailure { error ->
//                    _uiState.update {
//                        it.copy(
//                            isLoading = false,
//                            error = error.message ?: "Неизвестная ошибка",
////                            isLoginEnabled = it.isEmailValid && it.password.isNotEmpty()
//                        )
//                    }
//                }
        }
    }

    fun clearError() {
//        _uiState.update { it.copy(error = null) }
    }
}