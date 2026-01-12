package com.example.testtaskforeffectivemobile.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.auth_ui.presentation.screen.LoginScreen
import com.example.core_navigation.navigation.Routes
import com.example.courses.presentation.screen.CoursesScreen
import com.example.courses.presentation.view_model.CoursesViewModel
import com.example.favorites_ui.presentation.screen.FavoritesScreen
import com.example.favorites_ui.presentation.view_model.FavoritesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute in listOf(Routes.COURSES, Routes.FAVORITES, Routes.ACCOUNT)) {
                BottomNavigationBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(Routes.COURSES) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Routes.LOGIN,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Routes.LOGIN) {
                LoginScreen(
                    viewModel = koinViewModel(),  // ← вот так просто!
                    onLoginSuccess = {
                        navController.navigate(Routes.COURSES) {
                            popUpTo(Routes.LOGIN) { inclusive = true }
                        }
                    }
                )
            }
            composable(Routes.COURSES) {
                val viewModel: CoursesViewModel = koinViewModel()
                CoursesScreen(viewModel = viewModel)
            }
            composable(Routes.FAVORITES) {
                val viewModel: FavoritesViewModel = koinViewModel()
                FavoritesScreen(viewModel = viewModel)
            }
            composable(Routes.ACCOUNT) {
                Text("Аккаунт")
            }
        }
    }
}