package com.example.login.ui.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.login.ui.LoginScreen


fun NavGraphBuilder.loginNavGraph(
    navController: NavController
) {
    composable(LoginDestination.route) {
        LoginScreen(
            navController = navController,
            goToGeneralScreen = {

            }
        )
    }
}