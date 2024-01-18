package com.furkancosgun.expensetrackerapp.presentation.screen.addeditexpense

import android.net.Uri
import androidx.compose.material3.SnackbarHostState

data class AddEditExpenseScreenState(
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
    val uploadedImage: Uri? = null,
    val snackBarHostState: SnackbarHostState = SnackbarHostState(),
    val isOpenCategoryAlert: Boolean = false
)
