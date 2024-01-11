package com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.manual

import androidx.compose.ui.graphics.ImageBitmap

data class CreateManualExpenseScreenState(
    val categories: List<String> = mutableListOf("Food", "Travel"),
    val merchantName: String = "",
    val merchantNameError: String? = null,
    val amount: Double = 0.0,
    val amountError: String? = null,
    val date: String = "",
    val dateError: String? = null,
    val description: String = "",
    val category: String = "",
    val includeVat: Boolean = false,
    val vat: Double = 0.0,
    val uploadedImage: ImageBitmap? = null,
)
