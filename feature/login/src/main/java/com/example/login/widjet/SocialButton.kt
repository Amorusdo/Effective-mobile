package com.example.login.widjet

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.core_utils.extation.openLink

@Composable
fun SocialButton(
    url: String? = null,
    color: Color,
    onClick: (() -> Unit)? = null,
    modifier: Modifier,
    painter: Painter
) {
    val context = LocalContext.current

    val handleClick = {
        when {
            url != null -> openLink(context, url)
            onClick != null -> onClick()
        }
    }

    Button(
        onClick = { handleClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            ,
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = painter ,
            tint = Color.Unspecified , // если цвет уже в svg/png
            modifier = Modifier.size(32.dp) ,
            contentDescription = "Social Media"
        )
    }
}
