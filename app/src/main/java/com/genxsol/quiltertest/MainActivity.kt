package com.genxsol.quiltertest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.genxsol.quiltertest.core.designsystem.theme.QuilterTheme
import com.genxsol.quiltertest.navigation.QuilterNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuilterTheme {
                val navController = rememberNavController()
                QuilterNavigation(navController = navController)
            }
        }
    }
}