package com.example.ui.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavItemData(
    val route: String ,
    @DrawableRes val icon: Int ,
    @StringRes val labelRes: Int
)