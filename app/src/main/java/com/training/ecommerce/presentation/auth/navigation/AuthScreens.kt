package com.training.ecommerce.presentation.auth.navigation

sealed class AuthScreen(val route: String) {
    data object LoginScreen : AuthScreen("login_screen")
    data object RegisterScreen : AuthScreen("register_screen")
}