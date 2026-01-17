package com.example.auth_ui._.presentation.screen

import Black
import OKOrange
import TextGray
import TextWhite
import VKBlue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.auth_ui.R
import com.example.auth_ui._.presentation.view_model.AuthViewModel
import com.example.auth_ui._.presentation.widget.EmailTextField
import com.example.auth_ui._.presentation.widget.ForgotPasswordLink
import com.example.auth_ui._.presentation.widget.PasswordTextField
import com.example.auth_ui._.presentation.widget.PrimaryButton
import com.example.auth_ui._.presentation.widget.SignUpPrompt
import com.example.auth_ui._.presentation.widget.SocialButton
import com.example.common.constant.ApiConfig.OK_URL
import com.example.common.constant.ApiConfig.VK_URL
import com.example.core_ui.theme.alfa0_3
import com.example.core_ui.theme.f1
import com.example.core_ui.theme.height1
import com.example.core_ui.theme.height24
import com.example.core_ui.theme.height28
import com.example.core_ui.theme.height32
import com.example.core_ui.theme.height6
import com.example.core_ui.theme.height8
import com.example.core_ui.theme.padding16
import com.example.core_ui.theme.padding24
import com.example.core_ui.theme.padding8

@Composable
fun LoginScreen(
    viewModel: AuthViewModel ,
    onLoginSuccess: () -> Unit ,
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Обработка успешного входа
//    LaunchedEffect(uiState.user) {
//        if (uiState.user != null) {
//            onLoginSuccess()
//        }
//    }
//
////     Показ ошибок
//   LaunchedEffect(uiState.error) {
//        uiState.error?.let { error ->
//            snackbarHostState.showSnackbar(
//                message = error
//            )
//            viewModel.clearError()
//        }
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding16),
            verticalArrangement = Arrangement.Center
        ) {
            // Заголовок
            Text(
                text = stringResource(R.string.enter),
                style = MaterialTheme.typography.headlineLarge,
                color = TextWhite,
                modifier = Modifier.padding(bottom = padding24)
            )

            EmailTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChange(it) },
                isValid = uiState.isEmailValid,
            )
            Spacer(modifier = Modifier.height(height6))

            // Пароль поле
            PasswordTextField(
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChange(it) },
            )

            Spacer(modifier = Modifier.height(height24))

            // Кнопка "Вход"
            PrimaryButton(
                text = stringResource(R.string.enter),
                onClick = {
                    viewModel.onLoginClick()
                    onLoginSuccess()},
                enabled = uiState.isLoginEnabled,
                modifier = Modifier.padding(top = padding16)
            )

            // Индикатор загрузки
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = padding16),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = TextWhite)
                }
            }

            Spacer(modifier = Modifier.height(height8))

            SignUpPrompt(
                onSignUpClick = { /* навигация на регистрацию */ }
            )

            Spacer(modifier = Modifier.height(padding8))

            ForgotPasswordLink(
                onForgotPasswordClick = { /* навигация на восстановление пароля */ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(height28))

            HorizontalDivider(
                thickness = height1,
                color = TextGray.copy(alfa0_3)
            )

            Spacer(modifier = Modifier.height(height32))

            // Кнопки соцсетей
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(padding16)
            ) {
                // ВКонтакте
                SocialButton(
                    painter = painterResource(id = R.drawable.icon_vk),
                    color = VKBlue,
                    url = VK_URL,
                    modifier = Modifier.weight(f1)
                )

                // Одноклассники
                SocialButton(
                    painter = painterResource(id = R.drawable.icon_ok),
                    color = OKOrange,
                    url = OK_URL,
                    modifier = Modifier.weight(f1)
                )
            }
        }

        // Snackbar для ошибок
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(padding16)
        )
    }
}