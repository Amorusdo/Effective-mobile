package com.example.testtaskforeffectivemobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.login.ui.navigation.LoginDestination
import com.example.login.ui.navigation.loginNavGraph


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = LoginDestination.route
            ) {
                loginNavGraph(navController)
            }
        }
    }

}
