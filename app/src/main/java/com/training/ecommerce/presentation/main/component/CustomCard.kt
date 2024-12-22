package com.training.ecommerce.presentation.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.ecommerce.R
import com.training.ecommerce.ui.theme.ECommerceTheme

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp)
    ) {

        Box(modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.iv_sale_product),
                contentDescription = "sale image"
            )

            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(24.dp)
                    .wrapContentHeight()
            )

        }
    }
}


@PreviewLightDark
@Composable
private fun CardPreview() {
    ECommerceTheme {
        CustomCard(onClick = {}, title = "Super Flash Sale")
    }
}