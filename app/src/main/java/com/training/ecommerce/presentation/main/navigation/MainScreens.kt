package com.training.ecommerce.presentation.main.navigation

sealed class MainScreens(val route: String) {
    data object HomeScreen :
        MainScreens("home_screen")
}