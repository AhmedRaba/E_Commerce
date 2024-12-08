package com.training.ecommerce.presentation.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.ecommerce.R
import com.training.ecommerce.ui.theme.ECommerceTheme
import com.training.ecommerce.ui.theme.neutralDark
import com.training.ecommerce.ui.theme.neutralGrey
import com.training.ecommerce.ui.theme.neutralLight
import com.training.ecommerce.ui.theme.primaryBlue

@Composable
fun LoginScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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


        TextField(value = "",
            onValueChange = {},
            label = {
                Text(
                    text = "Your Email",
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = neutralGrey
                )
            }, leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_mail),
                    contentDescription = "auth icon",
                    tint = Color.Unspecified,
                )
            }, modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp, color = neutralLight, shape = RoundedCornerShape(5.dp)
                )
                .clip(RoundedCornerShape(5.dp)),
            shape = RoundedCornerShape(5.dp),
            colors = TextFieldDefaults
                .colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                )
        )
        Spacer(modifier = Modifier.height(8.dp))


        TextField(value = "",
            onValueChange = {},
            label = {
                Text(
                    text = "Your Password",
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = neutralGrey
                )
            }, leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = "auth icon",
                    tint = Color.Unspecified,
                )
            }, modifier = Modifier
                .fillMaxWidth()

                .border(
                    width = 1.dp, color = neutralLight, shape = RoundedCornerShape(5.dp)
                )
                .clip(RoundedCornerShape(5.dp)),
            shape = RoundedCornerShape(5.dp),
            colors = TextFieldDefaults
                .colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                )
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {},
            modifier = Modifier
                .size(width = Dp.Unspecified, height = 58.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryBlue,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Sign In",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(21.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            HorizontalDivider(
                color = neutralLight,
                thickness = 1.dp,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "OR",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = neutralGrey,
                modifier = Modifier.padding(horizontal = 18.dp)
            )

            HorizontalDivider(
                color = neutralLight,
                thickness = 1.dp,
                modifier = Modifier.weight(1f)
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp, color = neutralLight, shape = RoundedCornerShape(5.dp)
                ),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.background
            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "google icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )

                Text(
                    text = "Login with Google",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = neutralGrey,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp, color = neutralLight, shape = RoundedCornerShape(5.dp)
                ),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.background
            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "google icon",
                    tint = Color.Unspecified,
                    modifier = Modifier.align(Alignment.CenterStart)
                )

                Text(
                    text = "Login with facebook",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = neutralGrey,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Forgot Password?",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = primaryBlue,
            modifier = Modifier
                .clickable {

                }
        )


        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Don't have an account? ",
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                color = neutralGrey
            )

            Text(
                text = "Register",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = primaryBlue,
                modifier = Modifier
                    .clickable {

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