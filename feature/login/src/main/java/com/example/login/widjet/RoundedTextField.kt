package com.example.login.widjet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.core_ui.theme.LoginTitle

import com.example.core_ui.theme.loginTitle
import com.example.core_ui.theme.robotoFontFamily


@Composable
fun RoundedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        placeholder = {
            Text(
                text = placeholder,
                fontFamily =robotoFontFamily ,
                style =  LoginTitle
            )
        },
        singleLine = true,
        visualTransformation = if (isPassword)
            PasswordVisualTransformation()
        else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF2A2A2A),
            unfocusedContainerColor = Color(0xFF2A2A2A),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        shape = RoundedCornerShape(25.dp)
    )
}
