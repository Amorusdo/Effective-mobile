package com.example.testtaskforeffectivemobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.testtaskforeffectivemobile.login.navigation.LogInDestination
import com.example.testtaskforeffectivemobile.login.navigation.login

@Composable
fun NavHost(

) {
    val navController = rememberNavController()

    NavHost(
        navController = navController ,
        startDestination = LogInDestination.route
    ) {
        login(
            navController=navController,
            goToGeneralScreen = {},
        )

    }






    fun NavHostController.navigateLoginDestination() {
        navigate(LogInDestination.route)
    }

}