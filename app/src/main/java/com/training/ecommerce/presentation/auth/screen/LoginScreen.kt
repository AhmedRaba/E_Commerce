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
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.ecommerce.R
import com.training.ecommerce.presentation.auth.component.SocialLoginButton
import com.training.ecommerce.presentation.component.CustomButton
import com.training.ecommerce.presentation.component.CustomTextField
import com.training.ecommerce.ui.theme.ECommerceTheme
import com.training.ecommerce.ui.theme.neutralDark
import com.training.ecommerce.ui.theme.neutralGrey
import com.training.ecommerce.ui.theme.neutralLight
import com.training.ecommerce.ui.theme.primaryBlue

@Composable
fun LoginScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 68.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_app_auth),
                contentDescription = "auth icon",
            )
        }

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
            label = "Your Password",
            icon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            passwordVisibility = passwordVisibility,
            iconDescription = "password icon"
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(text = "Sign In", onClick = {})

        Spacer(modifier = Modifier.height(21.dp))

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
            iconDescription = "google icon"
        )

        Spacer(modifier = Modifier.height(8.dp))

        SocialLoginButton(
            text = "Login with facebook",
            icon = R.drawable.ic_facebook,
            iconDescription = "facebook icon"
        )

        Spacer(modifier = Modifier.height(16.dp))

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

                }

            )
        }

    }

}


@PreviewLightDark
@Composable
private fun LoginScreenPreview() {
    ECommerceTheme {
        LoginScreen()
    }
}