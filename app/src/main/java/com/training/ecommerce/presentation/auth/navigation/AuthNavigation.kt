package com.training.ecommerce.presentation.auth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.training.ecommerce.presentation.auth.screen.LoginScreen
import com.training.ecommerce.presentation.auth.screen.RegisterScreen

@Composable
fun AuthNavigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AuthScreen.LoginScreen.route) {
        composable(route = AuthScreen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = AuthScreen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }

    }
}