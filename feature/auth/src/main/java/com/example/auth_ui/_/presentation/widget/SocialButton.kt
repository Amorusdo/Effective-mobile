package com.example.auth_ui._.presentation.widget

import TextWhite
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
@Composable
fun SocialButton(
    painter: Painter ,
    color: Color ,
    url: String? = null ,
    modifier: Modifier,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current

    val handleClick: () -> Unit = {
        url?.let { openLink(context, it) } ?: onClick?.invoke()
    }


    Button(
        onClick = handleClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = painter,
            contentDescription = null, // соц. иконка — декоративная
            modifier = Modifier.size(32.dp),
            tint = TextWhite
        )
    }
}



@SuppressLint("UseKtx")
fun openLink(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}