package com.example.auth_ui._.presentation.view_model


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth_ui._.domain.model.User
import com.example.auth_ui._.domain.usecase.RegisterUseCase
import com.example.common.utils.toUserMessage
import com.example.common.utils.EmailValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val name: String = "",
    val isEmailValid: Boolean = false,
    val isRegisterEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: User? = null
)

class RegisterViewModel(
    application: Application ,
    private val registerUseCase: RegisterUseCase
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        val isValid = EmailValidator.isValid(email)
        _uiState.update {
            it.copy(
                email = email,
                isEmailValid = isValid,
                isRegisterEnabled = isFormValid(isValid, it.password, it.confirmPassword, it.name),
                error = null
            )
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                isRegisterEnabled = isFormValid(it.isEmailValid, password, it.confirmPassword, it.name),
                error = null
            )
        }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _uiState.update {
            it.copy(
                confirmPassword = confirmPassword,
                isRegisterEnabled = isFormValid(it.isEmailValid, it.password, confirmPassword, it.name),
                error = null
            )
        }
    }

    fun onNameChange(name: String) {
        _uiState.update {
            it.copy(
                name = name,
                isRegisterEnabled = isFormValid(it.isEmailValid, it.password, it.confirmPassword, name),
                error = null
            )
        }
    }

    fun onRegisterClick() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, isRegisterEnabled = false) }

            registerUseCase(
                email = _uiState.value.email,
                password = _uiState.value.password,
                confirmPassword = _uiState.value.confirmPassword,
                name = _uiState.value.name
            )
                .onSuccess { user ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            user = user,
                            error = null
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.toUserMessage(getApplication()),
                            isRegisterEnabled = isFormValid(
                                it.isEmailValid,
                                it.password,
                                it.confirmPassword,
                                it.name
                            )
                        )
                    }
                }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    private fun isFormValid(
        isEmailValid: Boolean,
        password: String,
        confirmPassword: String,
        name: String
    ): Boolean {
        return isEmailValid &&
                password.isNotEmpty() &&
                confirmPassword.isNotEmpty() &&
                name.isNotEmpty() &&
                password == confirmPassword
    }
}