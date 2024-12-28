package com.training.ecommerce.presentation.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.training.ecommerce.R
import com.training.ecommerce.ui.theme.ECommerceTheme

@Composable
fun SwipeableList(
    listType: ListType,
) {

    val categories = listOf(
        Pair(R.drawable.ic_shirt, "Man Shirt"),
        Pair(R.drawable.ic_tshirt, "Man T-Shirt"),
        Pair(R.drawable.ic_dress, "Dress"),
        Pair(R.drawable.ic_man_bag, "Man Work Equipment"),
        Pair(R.drawable.ic_woman_bag, "Woman Bag"),
        Pair(R.drawable.ic_man_shoes, "Man Shoes"),
        Pair(R.drawable.ic_man_pants, "Man Pants"),
        Pair(R.drawable.ic_man_underwear, "Man Underwear")
    )

    val products = listOf(
        Triple(R.drawable.iv_product, "Man Underwear", 20),
        Triple(R.drawable.iv_product, "Man Underwear", 20),
        Triple(R.drawable.iv_product, "Man Underwear", 20),
        Triple(R.drawable.iv_product, "Man Underwear", 20),
        Triple(R.drawable.iv_product, "Man Underwear", 20),
        Triple(R.drawable.iv_product, "Man Underwear", 20),
        Triple(R.drawable.iv_product, "Man Underwear", 20),
        Triple(R.drawable.iv_product, "Man Underwear", 20),
        Triple(R.drawable.iv_product, "Man Underwear", 20),

        )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        when (listType) {
            ListType.CATEGORY -> {
                items(categories) { category ->
                    val (iconRes, text) = category
                    CategoryItem(
                        iconRes = iconRes,
                        text = text,
                        onClick = {}
                    )
                }
            }

            ListType.FLASH_SALE -> {
                items(products) { product ->
                    val (iconRes, name, price) = product
                    ProductItem(
                        productImageRes = iconRes,
                        productName = name,
                        originalPrice = price.toDouble(),
                        showDiscount =true,
                        discountPrice = 15.0,
                        discountPercent = 25,
                        onClick = {}
                    )

                }
            }

            ListType.MEGA_SALE -> {
                items(products) { product ->
                    val (iconRes, name, price) = product
                    ProductItem(
                        productImageRes = iconRes,
                        productName = name,
                        originalPrice = price.toDouble(),
                        showDiscount = false,
                        onClick = {}
                    )

                }
            }
        }


    }

}


@PreviewLightDark
@Composable
private fun Preview() {
    ECommerceTheme {
        SwipeableList(ListType.CATEGORY)
    }

}