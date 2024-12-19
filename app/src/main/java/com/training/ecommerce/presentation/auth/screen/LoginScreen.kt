package com.training.ecommerce.presentation.auth.screen

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.training.ecommerce.R
import com.training.ecommerce.data.model.User
import com.training.ecommerce.data.preferences.UserPreferencesManager
import com.training.ecommerce.data.utils.Result
import com.training.ecommerce.presentation.auth.component.SocialLoginButton
import com.training.ecommerce.presentation.auth.navigation.AuthScreen
import com.training.ecommerce.presentation.auth.viewmodel.AuthViewModel
import com.training.ecommerce.presentation.component.CustomButton
import com.training.ecommerce.presentation.component.CustomTextField
import com.training.ecommerce.presentation.main.MainActivity
import com.training.ecommerce.ui.theme.neutralDark
import com.training.ecommerce.ui.theme.neutralGrey
import com.training.ecommerce.ui.theme.neutralLight
import com.training.ecommerce.ui.theme.primaryBlue

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var emailErrorMessage by remember { mutableStateOf("") }
    var passwordErrorMessage by remember { mutableStateOf("") }

    var isFormSubmitted by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }


    val authState by viewModel.authLoginState.collectAsState()

    var previousAuthState by remember { mutableStateOf<Result<User>?>(null) }

    val userPreferencesManager = UserPreferencesManager(context)

    DisposableEffect(navController) {
        onDispose {
            viewModel.resetAuthState()
        }
    }

    LaunchedEffect(authState) {
        if (authState != previousAuthState) {
            previousAuthState = authState
            when (authState) {
                is Result.Success -> {

                    snackBarHostState.currentSnackbarData?.dismiss()
                    snackBarHostState.showSnackbar("Login Successful")
                    emailError = false
                    passwordError = false

                    userPreferencesManager.setLoginStatus(true)

                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    (context as? Activity)?.finish()

                }

                is Result.Error -> {
                    emailError = true
                    passwordError = true
                    val errorMessage =
                        (authState as Result.Error).exception.message ?: "An unknown error occurred"
                    snackBarHostState.currentSnackbarData?.dismiss()
                    snackBarHostState.showSnackbar(errorMessage)

                }

                else -> {}
            }

        }


    }

    val isValidForm = if (isFormSubmitted) {
        validateForm(
            email = email,
            password = password,
            emailError = { emailError = it },
            passwordError = { passwordError = it },
            emailErrorMessage = { emailErrorMessage = it },
            passwordErrorMessage = { passwordErrorMessage = it },
        )
    } else {
        true
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {


        HeaderSection()

        FormSection(
            email = email,
            onEmailChange = { email = it },
            emailError = emailError,
            emailErrorMessage = emailErrorMessage,
            password = password,
            onPasswordChange = { password = it },
            passwordError = passwordError,
            passwordErrorMessage = passwordErrorMessage,
            passwordVisibility = passwordVisibility
        )

        SignInButton(onClick = {
            isFormSubmitted = true
            Log.e("LoginScreen", "Try to Login")
            if (isValidForm) {
                Log.e("LoginScreen", "Login")
                viewModel.login(email, password)
            }
        })


        SocialLoginSection(googleSignIn = {}, facebookSignIn = {})


        FooterSection(navController = navController)

        SnackbarHost(hostState = snackBarHostState)


    }
}

fun validateForm(
    email: String,
    password: String,
    emailError: (Boolean) -> Unit,
    emailErrorMessage: (String) -> Unit,
    passwordError: (Boolean) -> Unit,
    passwordErrorMessage: (String) -> Unit,
): Boolean {

    var isValid = true



    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        emailError(true)
        emailErrorMessage("Please enter a valid email")
        isValid = false
    } else {
        emailError(false)
    }

    if (password.isEmpty() || password.length < 6) {
        passwordError(true)
        passwordErrorMessage("Password should be at least 6 characters")
        isValid = false
    } else {
        passwordError(false)
    }

    Log.e("validateForm", "validateForm:$isValid ")

    return isValid
}

@Composable
private fun HeaderSection() {
    Image(
        modifier = Modifier.padding(top = 68.dp),
        painter = painterResource(id = R.drawable.ic_app_auth),
        contentDescription = "auth icon",
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Welcome to E-Commerce",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = neutralDark
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Sign in to continue",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = neutralGrey
    )

    Spacer(modifier = Modifier.height(28.dp))

}

@Composable
private fun FormSection(
    email: String,
    onEmailChange: (String) -> Unit,
    emailError: Boolean,
    emailErrorMessage: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordError: Boolean,
    passwordErrorMessage: String,
    passwordVisibility: MutableState<Boolean>,
) {
    CustomTextField(
        value = email,
        onValueChange = onEmailChange,
        label = "Your Email",
        icon = R.drawable.ic_mail,
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Next,
        iconDescription = "mail icon",
        error = emailError,
        errorMessage = emailErrorMessage
    )

    Spacer(modifier = Modifier.height(8.dp))

    CustomTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = "Your Password",
        icon = R.drawable.ic_lock,
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Done,
        passwordVisibility = passwordVisibility,
        iconDescription = "password icon",
        error = passwordError,
        errorMessage = passwordErrorMessage
    )

    Spacer(modifier = Modifier.height(16.dp))

}

@Composable
private fun SignInButton(onClick: () -> Unit) {
    CustomButton(text = "Sign In", onClick = onClick)
    Spacer(modifier = Modifier.height(21.dp))
}


@Composable
private fun SocialLoginSection(
    googleSignIn: () -> Unit,
    facebookSignIn: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {

        HorizontalDivider(
            color = neutralLight, thickness = 1.dp, modifier = Modifier.weight(1f)
        )

        Text(
            text = "OR",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = neutralGrey,
            modifier = Modifier.padding(horizontal = 18.dp)
        )

        HorizontalDivider(
            color = neutralLight, thickness = 1.dp, modifier = Modifier.weight(1f)
        )

    }

    Spacer(modifier = Modifier.height(16.dp))

    SocialLoginButton(
        text = "Login with Google",
        icon = R.drawable.ic_google,
        iconDescription = "google icon",
        onClick = googleSignIn
    )

    Spacer(modifier = Modifier.height(8.dp))

    SocialLoginButton(
        text = "Login with facebook",
        icon = R.drawable.ic_facebook,
        iconDescription = "facebook icon",
        onClick = facebookSignIn
    )

    Spacer(modifier = Modifier.height(16.dp))
}


@Composable
private fun FooterSection(navController: NavController) {
    Text(text = "Forgot Password?",
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        color = primaryBlue,
        modifier = Modifier.clickable {

        })

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Don't have an account? ",
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = neutralGrey
        )

        Text(text = "Register",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = primaryBlue,
            modifier = Modifier.clickable {
                navController.navigate(AuthScreen.RegisterScreen.route)
            }

        )
    }
}
