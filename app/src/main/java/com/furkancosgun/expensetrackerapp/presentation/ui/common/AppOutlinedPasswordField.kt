package com.furkancosgun.expensetrackerapp.presentation.ui.common

import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation


@Composable
fun AppOutlinedPasswordField(
    modifier: Modifier = Modifier,
    label: String = "",
    text: String,
    onTextChanged: (String) -> Unit,
    icon: ImageVector
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChanged,
        label = {
            Text(text = label)
        }, leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = label
            )
        },
        visualTransformation = PasswordVisualTransformation()
    )
}