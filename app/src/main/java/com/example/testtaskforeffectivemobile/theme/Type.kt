package com.example.testtaskforeffectivemobile.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
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
            fontFamily = wind_songFontFamily ,
            fontWeight = FontWeight(400) ,
            color = Dark_blue ,
            lineHeight = space16 ,
            letterSpacing = space05
        ),
    )
val Typography.registrationIsNotCheck: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = textSize16 ,
            fontFamily = aliceFontFamily ,
            fontWeight = FontWeight(400),
            color = Grey ,

        )
    }

val Typography.inputLabelStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = textSize14 ,
            fontFamily = aliceFontFamily ,
            fontWeight = FontWeight(400),
            color = Dark_blue ,
        )

    }
val Typography.textInDrawerMessage: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = textSize14 ,
            fontFamily = aliceFontFamily ,
            letterSpacing = 1.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight(400),
            color = Dark_blue ,)
    }

val Typography.textInRegistrationField: TextStyle
    @Composable
    get() {
        return TextStyle(
            textAlign = TextAlign.Start,
            fontSize = textSize20 ,
            fontFamily = aliceFontFamily ,
            fontWeight = FontWeight(700),
            color = Dark_blue ,
        )}

val Typography.textInSmsField: TextStyle
    @Composable
    get() {
        return TextStyle(
            textAlign = TextAlign.Center,
            fontSize = textSize20 ,
            fontFamily = aliceFontFamily ,
            fontWeight = FontWeight(700),
            color = Dark_blue ,
        )}
val Typography.textBold: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = textSize20 ,
            fontFamily = aliceFontFamily ,
            color = Dark_blue ,
        )}
val Typography.textInDialogButton: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = textSize14 ,
            fontFamily = aliceFontFamily ,
            color = Whitish ,
        )}

val Typography.textInDrawer: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = textSize28 ,
            fontFamily = aliceFontFamily ,
            color = Dark_blue ,
        )}

val Typography.registrationIsCheck: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = textSize32 ,
            fontFamily = aliceFontFamily ,
            fontWeight = FontWeight.Bold ,
            color = Dark_blue ,
        )
    }
///// generalScreen
val Typography.generalScreenBold: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = textSize20 ,
            fontFamily = aliceFontFamily ,
            fontWeight = FontWeight.Bold ,
            color = Dark_blue ,
            )
    }
val Typography.generalScreenNormal: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = textSize16 ,
            fontFamily = aliceFontFamily ,
            fontWeight = FontWeight.Normal ,
            color = Dark_blue ,
            )
    }
val Typography.generalScreenSmall: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = textSize16 ,
            fontFamily = aliceFontFamily ,
            fontWeight = FontWeight.Normal ,
            color = Dark_blue ,
            )
    }





val Typography.simpleText: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = textSize16 ,
            fontFamily = ofont_evolventaFontFamily ,
            fontWeight = FontWeight.Normal ,
            color = Dark_blue ,

            )}