package com.example.testtaskforeffectivemobile.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography: Typography
    get() = Typography(
        bodyLarge = TextStyle(
            color = Dark_blue ,
            fontFamily = FontFamily.Default ,
            fontWeight = FontWeight.Black ,
            fontSize = 22.sp ,
            lineHeight = 20.sp ,
            letterSpacing = 0.5.sp,

        ),

        titleLarge = TextStyle(
            color = Red ,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),

        labelSmall = TextStyle(
            fontSize = space16 ,
            fontFamily = robotoFontFamily ,
            fontWeight = FontWeight(400) ,
            color = Dark_blue ,
            lineHeight = space16 ,
            letterSpacing = space05
        ),
    )
