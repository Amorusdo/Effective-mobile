package com.example.auth_ui.presentation.widget

import GreenButton
import TextWhite
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.auth_ui.R
import com.example.core_ui.theme.textSize14

@Composable
fun SignUpPrompt(
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_account),
            color = TextWhite,
            fontSize = textSize14
        )
        Text(
            text = stringResource(R.string.registration),
            color = GreenButton,
            fontSize = textSize14,
            modifier = Modifier.clickable { onSignUpClick() }
        )
    }
}

