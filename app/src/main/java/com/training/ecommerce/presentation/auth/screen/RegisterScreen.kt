package com.training.ecommerce.presentation.auth.screen

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
import com.training.ecommerce.presentation.component.CustomButton
import com.training.ecommerce.presentation.component.CustomTextField
import com.training.ecommerce.ui.theme.neutralDark
import com.training.ecommerce.ui.theme.neutralGrey
import com.training.ecommerce.ui.theme.primaryBlue

@Composable
fun RegisterScreen(
    navController: NavController,
) {

    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }

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
            passwordVisibility = passwordVisibility
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
            passwordVisibility = passwordVisibility
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(text = "Sign Up", onClick = { /*TODO*/ })

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
