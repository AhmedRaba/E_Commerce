package com.training.ecommerce.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.ecommerce.ui.theme.neutralGrey
import com.training.ecommerce.ui.theme.neutralLight
import com.training.ecommerce.ui.theme.primaryBlue
import com.training.ecommerce.ui.theme.primaryRed

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: Int,
    iconDescription: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    passwordVisibility: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    error: Boolean = true,
    modifier: Modifier = Modifier,
) {
    val interactionSource =
        remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState()


    val borderColor = when {
        error -> primaryRed
        isFocused.value -> primaryBlue
        else -> neutralLight
    }
    val iconColor = when {
        error -> primaryRed
        isFocused.value -> primaryBlue
        else -> neutralGrey
    }

    TextField(value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                color = neutralGrey
            )
        }, leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = iconDescription,
                tint = iconColor,
            )
        }, modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(5.dp)
            )
            .clip(RoundedCornerShape(5.dp)),
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults
            .colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = Color.Transparent

            ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        visualTransformation = if (keyboardType == KeyboardType.Password && !passwordVisibility.value) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }, interactionSource = interactionSource
    )
}