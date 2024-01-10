package com.furkancosgun.expensetrackerapp.presentation.ui.common

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object UIConstants {
    val FAB_OFFSET_X = 0.dp
    val FAB_OFFSET_Y = 40.dp
}

enum class UIPadding(val size: Dp) {
    LARGE(16.dp),
    MEDIUM(8.dp),
    SMALL(4.dp),
    NONE(0.dp)
}

enum class UISpacing(val size: Dp) {
    EXTRA_LARGE(32.dp),
    LARGE(16.dp),
    MEDIUM(8.dp),
    SMALL(4.dp),
    NONE(0.dp)
}