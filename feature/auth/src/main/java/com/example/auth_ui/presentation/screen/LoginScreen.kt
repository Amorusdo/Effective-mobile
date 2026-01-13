package com.example.auth_ui.presentation.screen

import Black
import GreenButton
import OKOrange
import SurfaceDark
import TextGray
import TextHint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.auth_ui.R
import com.example.auth_ui.presentation.view_model.AuthViewModel
import com.example.auth_ui.presentation.widget.SocialButton
import com.example.core_ui.theme.Typography

@Composable
fun LoginScreen(
    viewModel: AuthViewModel ,
    onLoginSuccess: () -> Unit ,
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(16.dp) ,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            // Заголовок
            Text(
                text = "Вход" ,
                style = MaterialTheme.typography.headlineLarge ,
                color = TextWhite ,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Email поле
            Text(
                text = "Email" ,
                style = Typography.labelLarge ,
                color = TextWhite
            )
            Spacer(modifier = Modifier.height(6.dp))

            Surface(
                shape = RoundedCornerShape(28.dp),
            ) {
            OutlinedTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = {
                    Text(
                        text = "example@gmail.com",
                        color = TextHint
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                isError = uiState.email.isNotEmpty() && !uiState.isEmailValid,
                trailingIcon = {
                    when {
                        uiState.email.isNotEmpty() && uiState.isEmailValid -> {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Email valid",
                                tint = GreenButton
                            )
                        }

                        uiState.email.isNotEmpty() && !uiState.isEmailValid -> {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = "Email invalid",
                                tint = Color.Red
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    errorBorderColor = Color.Transparent,

                    focusedContainerColor = SurfaceDark,
                    unfocusedContainerColor = SurfaceDark,
                    errorContainerColor = SurfaceDark,

                    focusedTextColor = TextWhite,
                    unfocusedTextColor = TextWhite,
                    errorTextColor = TextWhite,

                    cursorColor = GreenButton
                ),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            )}

            Spacer(modifier = Modifier.height(6.dp))

            // Пароль поле
            Text(
                text = "Пароль" ,
                style = Typography.labelLarge ,
                color = TextWhite ,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = uiState.password ,
                onValueChange = { viewModel.onPasswordChange(it) } ,
                placeholder = {
                    Text(
                        "Введите пароль" ,
                        color = TextHint
                    )
                } ,
                visualTransformation = PasswordVisualTransformation() ,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ) ,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent ,
                    unfocusedBorderColor = Color.Transparent ,
                    focusedContainerColor = SurfaceDark ,
                    unfocusedContainerColor = SurfaceDark ,
                    focusedTextColor = TextWhite ,
                    unfocusedTextColor = TextWhite
                ) ,
                shape = RoundedCornerShape(28.dp) ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp) ,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Кнопка "Вход"
            Button(
                onClick = {
                    viewModel.onLoginClick()
                    onLoginSuccess()
                } ,
                enabled = uiState.isLoginEnabled ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenButton ,
                    contentColor = TextWhite ,
                    disabledContainerColor = GreenButton.copy(alpha = 0.5f) ,
                    disabledContentColor = TextWhite.copy(alpha = 0.5f)
                ) ,
                shape = RoundedCornerShape(28.dp) ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    "Вход" ,
                    fontSize = 18.sp ,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Нет аккаунта? " ,
                    color = TextWhite ,
                    fontSize = 13.sp
                )
                Text(
                    text = "Регистрация" ,
                    color = GreenButton ,
                    fontSize = 13.sp ,
                    modifier = Modifier
                )
            }

            Text(
                text = "Забыл пароль" ,
                color = GreenButton ,
                fontSize = 13.sp ,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)

            )

            Spacer(modifier = Modifier.height(28.dp))


            HorizontalDivider(
                thickness = 1.dp ,
                color = TextGray.copy(alpha = 0.3f)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Кнопки соцсетей
            Row(
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // ВКонтакте
                SocialButton(
                    painter = painterResource(id = R.drawable.icon_vk) ,
                    color = VKBlue ,
                    url = "https://vk.com/" ,
                    modifier = Modifier.weight(1f)

                )

                // Одноклассники
                SocialButton(
                    painter = painterResource(id = R.drawable.icon_ok) ,
                    color = OKOrange ,
                    url = "https://ok.ru/" ,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}