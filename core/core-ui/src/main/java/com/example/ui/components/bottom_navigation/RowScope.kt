package com.example.ui.components.bottom_navigation

import Green
import Transparent
import White
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun RowScope.BottomNavItem(
    route: String ,
    currentRoute: String? ,
    @DrawableRes icon: Int ,
    label: String ,
    onNavigate: (String) -> Unit
) {
    val isSelected = currentRoute == route

    NavigationBarItem(
        icon = {
            Icon(
                painter = painterResource(icon),
                contentDescription = label,
                tint = if (isSelected) Green else White
            )
        },
        label = {
            Text(
                text = label,
                color = if (isSelected) Green else White
            )
        },
        selected = isSelected,
        onClick = { onNavigate(route) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Green,
            selectedTextColor = Green,
            unselectedIconColor = White,
            unselectedTextColor = White,
            indicatorColor = Transparent
        )
    )
}