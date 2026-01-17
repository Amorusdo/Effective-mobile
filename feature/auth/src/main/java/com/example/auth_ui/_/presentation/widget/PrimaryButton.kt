package com.example.auth_ui._.presentation.widget

import GreenButton
import TextWhite
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.core_ui.theme.Typography
import com.example.core_ui.theme.alfa0_5
import com.example.core_ui.theme.height52
import com.example.core_ui.theme.size28
import com.example.core_ui.theme.textSize18

@Composable
fun PrimaryButton(
    text: String ,
    onClick: () -> Unit ,
    modifier: Modifier = Modifier ,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenButton,
            contentColor = TextWhite,
            disabledContainerColor = GreenButton.copy(alfa0_5),
            disabledContentColor = TextWhite.copy(alfa0_5)
        ),
        shape = RoundedCornerShape(size28),
        modifier = modifier
            .fillMaxWidth()
            .height(height52)
    ) {
        Text(
            text = text,
            fontSize = textSize18 ,
            style = Typography.labelLarge
        )
    }
}