package com.example.auth_ui.presentation.widget

import GreenButton
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.auth_ui.R
import com.example.core_ui.theme.textSize14

@Composable
fun ForgotPasswordLink(
    onForgotPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.fogot_password),
        color = GreenButton,
        fontSize = textSize14,
        modifier = modifier.clickable { onForgotPasswordClick() }
    )
}