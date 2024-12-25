package com.training.ecommerce.presentation.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.ecommerce.ui.theme.neutralDark
import com.training.ecommerce.ui.theme.neutralGrey
import com.training.ecommerce.ui.theme.neutralLight
import com.training.ecommerce.ui.theme.primaryBlue
import com.training.ecommerce.ui.theme.primaryRed

@Composable
fun ProductItem(
    productImageRes: Int,
    productName: String,
    originalPrice: Double,
    onClick: () -> Unit,
) {

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 2.dp,
                color = neutralLight,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        shape = RectangleShape,
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                modifier = Modifier
                    .height(130.dp)
                    .align(Alignment.CenterHorizontally)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(
                    id = productImageRes
                ),
                contentDescription = null
            )

            Text(
                text = productName,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = neutralDark
            )

            Text(
                text = "\$$originalPrice",
                fontWeight = FontWeight.Bold,
                color = primaryBlue,
                fontSize = 12.sp
            )

            if (true) {
                Row {
                    Text(
                        text = "\$$originalPrice",
                        fontWeight = FontWeight.Light,
                        fontSize = 10.sp,
                        color = neutralGrey,
                        textDecoration = TextDecoration.LineThrough
                    )

                    Text(
                        text = " Discount",
                        fontWeight = FontWeight.Bold,
                        color = primaryRed,
                        fontSize = 10.sp
                    )
                }
            }

        }


    }
}
