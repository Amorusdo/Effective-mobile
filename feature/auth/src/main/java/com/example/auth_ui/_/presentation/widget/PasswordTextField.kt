package com.example.auth_ui._.presentation.widget

import GreenButton
import SurfaceDark
import TextHint
import TextWhite
import Transparent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.auth_ui.R
import com.example.core_ui.theme.Typography
import com.example.core_ui.theme.height52
import com.example.core_ui.theme.height6
import com.example.core_ui.theme.padding8
import com.example.core_ui.theme.size28

@Composable
fun PasswordTextField(
    value: String ,
    onValueChange: (String) -> Unit ,
    modifier: Modifier = Modifier ,
    label: String = stringResource(R.string.password) ,
    placeholder: String = stringResource(R.string.enter_password)
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = Typography.labelLarge,
            color = TextWhite,
            modifier = Modifier.padding(top = padding8)
        )
        Spacer(modifier = Modifier.height(height6))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = TextHint
                )
            },
            visualTransformation = PasswordVisualTransformation() ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ) ,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Transparent,
                unfocusedBorderColor = Transparent,
                focusedContainerColor = SurfaceDark,
                unfocusedContainerColor = SurfaceDark,
                focusedTextColor = TextWhite,
                unfocusedTextColor = TextWhite,
                cursorColor = GreenButton
            ),
            shape = RoundedCornerShape(size28),
            modifier = Modifier
                .fillMaxWidth()
                .height(height52),
            singleLine = true
        )
    }
}