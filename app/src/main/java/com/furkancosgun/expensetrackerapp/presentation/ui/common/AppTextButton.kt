package com.furkancosgun.expensetrackerapp.presentation.ui.common

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AppTextButton(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.End,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(
            modifier = modifier,
            text = text,
            textAlign = textAlign
        )
    }
}