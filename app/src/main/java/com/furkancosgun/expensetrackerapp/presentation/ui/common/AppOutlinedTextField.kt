package com.furkancosgun.expensetrackerapp.presentation.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ErrorColor

@Composable
fun AppOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    text: String,
    onTextChanged: (String) -> Unit,
    errorText: String? = null,
    icon: ImageVector
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
        isError = errorText != null,
    )
    AnimatedVisibility(visible = errorText != null) {
        Text(text = errorText ?: "", color = ErrorColor)
    }
}