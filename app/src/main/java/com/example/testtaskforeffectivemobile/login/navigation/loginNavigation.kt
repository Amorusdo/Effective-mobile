package com.example.testtaskforeffectivemobile.login.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.testtaskforeffectivemobile.constanse.Constants
import com.example.testtaskforeffectivemobile.core_navigation.NavDestination
import com.example.testtaskforeffectivemobile.login.LoginScreen

fun NavGraphBuilder.login (
    navController: NavController,
    goToGeneralScreen: () -> Unit,

)
  {
    composable(route =  LogInDestination.route) {
       LoginScreen(
navController =navController,
           goToGeneralScreen=goToGeneralScreen,

       )

    }
}
object LogInDestination : NavDestination {
    override val route = Constants.Screens.LOGIN
}