package com.training.ecommerce.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.training.ecommerce.presentation.main.screen.HomeScreen

@Composable
fun HomeNavigation(
    modifier: Modifier = Modifier,
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MainScreen.HomeScreen.route) {

        composable(route = MainScreen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }


    }
}