package com.training.ecommerce.presentation.auth.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.ecommerce.ui.theme.neutralGrey
import com.training.ecommerce.ui.theme.neutralLight

@Composable
fun SocialLoginButton(
    text: String,
    icon: Int,
    iconDescription: String,
    modifier: Modifier = Modifier,
) {

    Button(
        onClick = {}, modifier = modifier
            .height(58.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp, color = neutralLight, shape = RoundedCornerShape(5.dp)
            ), shape = RoundedCornerShape(5.dp), colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.background
        )
    ) {

        Box(
            modifier = modifier.fillMaxSize()
        ) {

            Icon(
                painter = painterResource(id = icon),
                contentDescription = iconDescription,
                tint = Color.Unspecified,
                modifier = modifier.align(Alignment.CenterStart)
            )

            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = neutralGrey,
                modifier = modifier.align(Alignment.Center)
            )
        }

    }


}