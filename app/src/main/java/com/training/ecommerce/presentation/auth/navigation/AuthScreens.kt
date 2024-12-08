package com.training.ecommerce.presentation.auth.navigation

sealed class AuthScreen(val route: String) {
    object LoginScreen : AuthScreen("login_screen")
    object RegisterScreen : AuthScreen("register_screen")
}