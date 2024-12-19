package com.training.ecommerce.presentation.auth.screen

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.training.ecommerce.R
import com.training.ecommerce.data.utils.Result
import com.training.ecommerce.presentation.auth.viewmodel.AuthViewModel
import com.training.ecommerce.presentation.component.CustomButton
import com.training.ecommerce.presentation.component.CustomTextField
import com.training.ecommerce.ui.theme.neutralDark
import com.training.ecommerce.ui.theme.neutralGrey
import com.training.ecommerce.ui.theme.primaryBlue

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: AuthViewModel,
) {

    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var userNameError by remember { mutableStateOf(false) }
    var passwordConfirmError by remember { mutableStateOf(false) }
    var emailErrorMessage by remember { mutableStateOf("") }
    var passwordErrorMessage by remember { mutableStateOf("") }
    var userNameErrorMessage by remember { mutableStateOf("") }
    var passwordConfirmErrorMessage by remember { mutableStateOf("") }

    var isFormSubmitted by remember { mutableStateOf(false) }

    val authState by viewModel.authRegisterState.collectAsState()

    val snackBarHostState = remember { SnackbarHostState() }


    DisposableEffect(navController) {
        onDispose {
            viewModel.resetAuthState()
        }
    }


    val isValidForm = if (isFormSubmitted) {
        validateForm(userName = userName,
            email = email,
            password = password,
            passwordConfirm = passwordConfirm,
            userNameError = { userNameError = it },
            emailError = { emailError = it },
            passwordError = { passwordError = it },
            passwordConfirmError = { passwordConfirmError = it },
            userNameErrorMessage = { userNameErrorMessage = it },
            emailErrorMessage = { emailErrorMessage = it },
            passwordErrorMessage = { passwordErrorMessage = it },
            passwordConfirmErrorMessage = { passwordConfirmErrorMessage = it })
    } else {
        true
    }



    LaunchedEffect(authState) {
        when (authState) {
            is Result.Success -> {
                snackBarHostState.showSnackbar("Check your email to verify")
                navController.popBackStack()
                userNameError = false
                emailError = false
                passwordError = false
            }

            is Result.Error -> {
                userNameError = true
                emailError = true
                passwordError = true

                val errorMessage =
                    (authState as Result.Error).exception.message ?: "An unknown error occurred"
                snackBarHostState.showSnackbar("Register Failed: $errorMessage")
            }

            else -> {}
        }

    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        HeaderSection()

        FormSection(
            userName = userName,
            userNameChange = { userName = it },
            userNameError = userNameError,
            userNameErrorMessage = userNameErrorMessage,
            email = email,
            onEmailChange = { email = it },
            emailError = emailError,
            emailErrorMessage = emailErrorMessage,
            password = password,
            onPasswordChange = { password = it },
            passwordError = passwordError,
            passwordErrorMessage = passwordErrorMessage,
            passwordVisibility = passwordVisibility,
            passwordConfirm = passwordConfirm,
            onPasswordConfirmChange = { passwordConfirm = it },
            passwordConfirmError = passwordConfirmError,
            passwordConfirmErrorMessage = passwordErrorMessage,
        )


        SignUpButton(onClick = {
            isFormSubmitted = true
            Log.e("RegisterScreen", "Try to Register: ")
            if (isValidForm) {
                Log.e("RegisterScreen", "Register: ")
                viewModel.register(userName, email, password)
            }
        })


        FooterSection(navController = navController)

        SnackbarHost(hostState = snackBarHostState)
    }


}


fun validateForm(
    userName: String = "",
    email: String,
    password: String,
    passwordConfirm: String = "",
    userNameError: (Boolean) -> Unit = {},
    userNameErrorMessage: (String) -> Unit = {},
    emailError: (Boolean) -> Unit,
    emailErrorMessage: (String) -> Unit,
    passwordError: (Boolean) -> Unit,
    passwordErrorMessage: (String) -> Unit,
    passwordConfirmError: (Boolean) -> Unit = {},
    passwordConfirmErrorMessage: (String) -> Unit = {},
): Boolean {

    var isValid = true

    if (userName.isEmpty()) {
        userNameError(true)
        userNameErrorMessage("Name cannot be empty")
        isValid = false
    } else {
        userNameError(false)
    }

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

    if (password != passwordConfirm) {
        passwordConfirmError(true)
        passwordConfirmErrorMessage("Passwords do not match")
        isValid = false
    } else {
        passwordConfirmError(false)
    }
    Log.e("validateForm", "isValid: $isValid ")
    return isValid
}


@Composable
private fun HeaderSection() {
    Image(
        modifier = Modifier.padding(top = 155.dp),
        painter = painterResource(id = R.drawable.ic_app_auth),
        contentDescription = "auth icon",
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Let's Get Started",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = neutralDark
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Create new Account",
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = neutralGrey
    )
    Spacer(modifier = Modifier.height(28.dp))
}

@Composable
private fun FormSection(
    userName: String,
    userNameChange: (String) -> Unit,
    userNameError: Boolean,
    userNameErrorMessage: String,
    email: String,
    onEmailChange: (String) -> Unit,
    emailError: Boolean,
    emailErrorMessage: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordError: Boolean,
    passwordErrorMessage: String,
    passwordVisibility: MutableState<Boolean>,
    passwordConfirm: String,
    onPasswordConfirmChange: (String) -> Unit,
    passwordConfirmError: Boolean,
    passwordConfirmErrorMessage: String,
) {
    CustomTextField(
        value = userName,
        onValueChange = userNameChange,
        label = "Full Name",
        icon = R.drawable.ic_user,
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next,
        iconDescription = "mail icon",
        error = userNameError,
        errorMessage = userNameErrorMessage
    )

    Spacer(modifier = Modifier.height(8.dp))

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
        label = "Password",
        icon = R.drawable.ic_lock,
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Next,
        iconDescription = "mail icon",
        passwordVisibility = passwordVisibility,
        error = passwordError,
        errorMessage = passwordErrorMessage
    )

    Spacer(modifier = Modifier.height(8.dp))

    CustomTextField(
        value = passwordConfirm,
        onValueChange = onPasswordConfirmChange,
        label = "Password Again",
        icon = R.drawable.ic_lock,
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Done,
        iconDescription = "mail icon",
        passwordVisibility = passwordVisibility,
        error = passwordConfirmError,
        errorMessage = passwordConfirmErrorMessage
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun SignUpButton(onClick: () -> Unit) {
    CustomButton(
        text = "Sign Up", onClick = onClick
    )

    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun FooterSection(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = "have an account? ",
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = neutralGrey
        )

        Text(text = "Sign In",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = primaryBlue,
            modifier = Modifier.clickable {
                navController.popBackStack()
            }

        )
    }
}