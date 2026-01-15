package com.example.testtaskforeffectivemobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.core_ui.theme.TestTaskForEffectiveMobileTheme
import com.example.testtaskforeffectivemobile.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestTaskForEffectiveMobileTheme {
                AppNavigation()

            }
        }
    }
}




