package com.furkancosgun.expensetrackerapp.presentation.ui.common

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape

@Composable
fun AppButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RectangleShape
    ) {
        Text(text = text)
    }
}