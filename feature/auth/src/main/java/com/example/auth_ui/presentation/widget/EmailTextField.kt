package com.example.auth_ui.presentation.widget

import GreenButton
import Red
import SurfaceDark
import TextHint
import TextWhite
import Transparent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.auth_ui.R
import com.example.common.utils.InputFilters
import com.example.core_ui.theme.Typography
import com.example.core_ui.theme.height52
import com.example.core_ui.theme.height6
import com.example.core_ui.theme.size28

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(com.example.common.R.string.email),
            style = Typography.labelLarge,
            color = TextWhite
        )
        Spacer(modifier = Modifier.height(height6))

        Surface(
            shape = RoundedCornerShape(size28),
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = { newValue ->
                    onValueChange(InputFilters.filterEmail(newValue))
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.example_gmail_com),
                        color = TextHint
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ) ,
                isError = value.isNotEmpty() && !isValid,
                trailingIcon = {
                    when {
                        value.isNotEmpty() && isValid -> {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = stringResource(R.string.email_valid),
                                tint = GreenButton
                            )
                        }
                        value.isNotEmpty() && !isValid -> {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = stringResource(R.string.email_invalid),
                                tint = Red
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Transparent,
                    unfocusedBorderColor = Transparent,
                    errorBorderColor = Transparent,

                    focusedContainerColor = SurfaceDark,
                    unfocusedContainerColor = SurfaceDark,
                    errorContainerColor = SurfaceDark,

                    focusedTextColor = TextWhite,
                    unfocusedTextColor = TextWhite,
                    errorTextColor = TextWhite,

                    cursorColor = GreenButton
                ),
                shape = RoundedCornerShape(size28),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height52)
            )
        }
    }
}