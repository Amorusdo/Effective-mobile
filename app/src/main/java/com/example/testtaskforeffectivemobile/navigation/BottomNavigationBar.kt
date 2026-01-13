package com.example.testtaskforeffectivemobile.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.core_navigation.navigation.Routes
import com.example.testtaskforeffectivemobile.R
import com.example.testtaskforeffectivemobile.theme.BackgroundDark
import com.example.testtaskforeffectivemobile.theme.Green
import com.example.testtaskforeffectivemobile.theme.Transparent
import com.example.testtaskforeffectivemobile.theme.White

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = BackgroundDark ,
        contentColor = White
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_state_layer),
                    contentDescription = "Главная",
                    tint = if (currentRoute == Routes.COURSES) Green else White
                )
            },
            label = {
                Text(
                    "Главная",
                    color = if (currentRoute == Routes.COURSES) Green else White
                )
            },
            selected = currentRoute == Routes.COURSES,
            onClick = { onNavigate(Routes.COURSES) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Green,
                selectedTextColor = Green,
                unselectedIconColor = White,
                unselectedTextColor = White,
                indicatorColor = Transparent
            )
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_bookmarker),
                    contentDescription = "Избранное",
                    tint = if (currentRoute == Routes.FAVORITES) Green else White
                )
            },
            label = {
                Text(
                    "Избранное",
                    color = if (currentRoute == Routes.FAVORITES) Green else White
                )
            },
            selected = currentRoute == Routes.FAVORITES,
            onClick = { onNavigate(Routes.FAVORITES) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Green,
                selectedTextColor = Green,
                unselectedIconColor = White,
                unselectedTextColor =White,
                indicatorColor = Transparent
            )
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_accout),
                    contentDescription = "Аккаунт",
                    tint = if (currentRoute == Routes.ACCOUNT) Green else White
                )
            },
            label = {
                Text(
                    "Аккаунт",
                    color = if (currentRoute == Routes.ACCOUNT) Green else White
                )
            },
            selected = currentRoute == Routes.ACCOUNT,
            onClick = { onNavigate(Routes.ACCOUNT) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Green,
                selectedTextColor = Green,
                unselectedIconColor = White,
                unselectedTextColor = White,
                indicatorColor = Transparent
            )
        )
    }
}