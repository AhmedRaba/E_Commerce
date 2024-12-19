package com.training.ecommerce.presentation.auth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.training.ecommerce.data.preferences.UserPreferencesManager
import com.training.ecommerce.presentation.auth.navigation.AuthNavigation
import com.training.ecommerce.presentation.main.MainActivity
import com.training.ecommerce.ui.theme.ECommerceTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            if (getLoginState()) {
                navigateToMainActivity()
            } else {
                setupLoginScreen()
            }
        }

    }

    private suspend fun getLoginState(): Boolean {
        val userPreferencesManager = UserPreferencesManager(this)
        return userPreferencesManager.loginStatus.first()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@AuthActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(OVERRIDE_TRANSITION_CLOSE, 0, 0)
        }else{
            overridePendingTransition(0, 0)
        }
        finish()
    }


    private fun setupLoginScreen() {
        enableEdgeToEdge()
        setContent {
            ECommerceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AuthNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }

    }

}