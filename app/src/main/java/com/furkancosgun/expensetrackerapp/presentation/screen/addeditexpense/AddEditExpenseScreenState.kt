package com.furkancosgun.expensetrackerapp.presentation.screen.addeditexpense

import androidx.compose.material3.SnackbarHostState
import com.furkancosgun.expensetrackerapp.domain.model.KeyValue

data class AddEditExpenseScreenState(
    val categories: List<KeyValue<String, String>> = mutableListOf(),
    val projects: List<KeyValue<String, String>> = mutableListOf(),
    val merchantName: String = "",
    val amount: Double = 0.0,
    val date: String = "",
    val description: String = "",
    val category: String = "",
    val categoryId: String = "",
    val project: String = "",
    val projectId: String = "",
    val includeVat: Boolean = false,
    val vat: Double = 0.0,
    val snackBarHostState: SnackbarHostState = SnackbarHostState(),
    val isOpenCategoryAlert: Boolean = false
)
