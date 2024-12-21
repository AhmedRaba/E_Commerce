package com.training.ecommerce.presentation.main.navigation

sealed class MainScreen(val route: String) {
    data object HomeScreen :
        MainScreen("home_screen")
}