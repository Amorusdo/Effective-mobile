package com.example.testtaskforeffectivemobile.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.core_navigation.navigation.Routes

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Text("🏠") }, // Замени на иконки из Figma
            label = { Text("Главная") },
            selected = currentRoute == Routes.COURSES,
            onClick = { onNavigate(Routes.COURSES) }
        )
        NavigationBarItem(
            icon = { Text("⭐") },
            label = { Text("Избранное") },
            selected = currentRoute == Routes.FAVORITES,
            onClick = { onNavigate(Routes.FAVORITES) }
        )
        NavigationBarItem(
            icon = { Text("👤") },
            label = { Text("Аккаунт") },
            selected = currentRoute == Routes.ACCOUNT,
            onClick = { onNavigate(Routes.ACCOUNT) }
        )
    }
}