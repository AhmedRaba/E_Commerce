package com.training.ecommerce.presentation.main.screen

import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.ecommerce.R
import com.training.ecommerce.presentation.component.CustomTextField
import com.training.ecommerce.presentation.main.component.CustomCard
import com.training.ecommerce.presentation.main.component.ListType
import com.training.ecommerce.presentation.main.component.SwipeableCategoryList
import com.training.ecommerce.ui.theme.ECommerceTheme
import com.training.ecommerce.ui.theme.neutralDark
import com.training.ecommerce.ui.theme.neutralLight
import com.training.ecommerce.ui.theme.primaryBlue

@Composable
fun HomeScreen(
) {

    Scaffold(topBar = { Header() }, content = { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            CustomCard(title = "Super Flash Sale", onClick = {})

            Spacer(modifier = Modifier.height(48.dp))

            CategorySection()

            SaleProductsSection(title = "Flash Sale", ListType.FLASH_SALE)

            SaleProductsSection(title = "Mega Sale", ListType.MEGA_SALE)

        }
    })


}


@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    ECommerceTheme {
        HomeScreen()
    }
}


@Composable
fun Header() {

    var search by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomTextField(
                value = search,
                onValueChange = { search = it },
                label = "Search Product",
                icon = R.drawable.ic_search,
                iconDescription = "search icon",
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
                onImeAction = { Toast.makeText(context, "SEARCH", Toast.LENGTH_SHORT).show() },
                modifier = Modifier.weight(1f)
            )

            Icon(painter = painterResource(id = R.drawable.ic_favorite),
                contentDescription = "favorite icon",
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(24.dp)
                    .clickable {})

            Icon(painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "notification icon",
                tint = Color.Unspecified,
                modifier = Modifier
                    .clickable {}
                    .size(24.dp)

            )
        }

        HorizontalDivider(
            color = neutralLight,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }


}

@Composable
private fun CategorySection() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Category", fontWeight = FontWeight.Bold, color = neutralDark, fontSize = 14.sp
        )

        Text(text = "More Category",
            fontWeight = FontWeight.Bold,
            color = primaryBlue,
            fontSize = 14.sp,
            modifier = Modifier.clickable { })
    }

    Spacer(modifier = Modifier.height(12.dp))

    SwipeableCategoryList(ListType.CATEGORY)

    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun SaleProductsSection(
    title: String,
    listType: ListType,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title, fontWeight = FontWeight.Bold,
            color = neutralDark, fontSize = 14.sp
        )

        Text(text = "See More",
            fontWeight = FontWeight.Bold,
            color = primaryBlue,
            fontSize = 14.sp,
            modifier = Modifier.clickable { }
        )
    }

    Spacer(modifier = Modifier.height(12.dp))

    SwipeableCategoryList(listType)

    Spacer(modifier = Modifier.height(24.dp))

}


