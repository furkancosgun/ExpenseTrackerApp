package com.furkancosgun.expensetrackerapp.presentation.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ErrorColor
import androidx.compose.material3.Text as Text1


@Composable
fun AppOutlinedPasswordField(
    modifier: Modifier = Modifier,
    label: String = "",
    text: String,
    onTextChanged: (String) -> Unit,
    errorText: String? = null,
    icon: ImageVector
) {
    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChanged,
        label = {
            Text1(text = label)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = label,
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (passwordVisibility) {
                        stringResource(R.string.hide_password)
                    } else {
                        stringResource(R.string.show_password)
                    },
                )
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        isError = errorText != null,
    )
    AnimatedVisibility(visible = errorText != null) {
        Text1(text = errorText ?: "", color = ErrorColor)
    }

}