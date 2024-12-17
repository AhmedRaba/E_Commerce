package com.training.ecommerce.presentation.auth.screen

import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavController
import com.training.ecommerce.R
import com.training.ecommerce.data.utils.Result
import com.training.ecommerce.data.utils.validateForm
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

    val context = LocalContext.current


    DisposableEffect(navController) {
        onDispose {
            viewModel.resetAuthState()
        }
    }


    val isValidForm = if (isFormSubmitted) {
        validateForm(
            userName = userName,
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
            passwordConfirmErrorMessage = { passwordConfirmErrorMessage = it }
        )
    } else {
        true
    }



    LaunchedEffect(authState) {
        when (authState) {
            is Result.Success -> {
                Toast.makeText(
                    context, "Check your email to verify", Toast.LENGTH_SHORT
                ).show()
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
                Toast.makeText(context, "Login Failed: $errorMessage", Toast.LENGTH_SHORT).show()
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

        CustomTextField(
            value = userName,
            onValueChange = { userName = it },
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
            onValueChange = { email = it },
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
            onValueChange = { password = it },
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
            onValueChange = { passwordConfirm = it },
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


        //SignUp
        CustomButton(text = "Sign Up", onClick = {
            isFormSubmitted = true
            if (isValidForm) {
                viewModel.register(userName, email, password)
            }
        })



        Spacer(modifier = Modifier.height(24.dp))

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


}
