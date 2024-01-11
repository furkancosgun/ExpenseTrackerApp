package com.furkancosgun.expensetrackerapp.presentation.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ErrorColor

@Composable
fun AppOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    onTextChanged: (String) -> Unit,
    icon: ImageVector,
    errorText: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChanged,
        label = {
            Text(text = label)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = label
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        isError = errorText != null,
        singleLine = true
    )
    AnimatedVisibility(visible = errorText != null) {
        Text(text = errorText ?: "", color = ErrorColor)
    }
}