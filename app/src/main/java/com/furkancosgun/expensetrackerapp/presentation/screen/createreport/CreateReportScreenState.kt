package com.furkancosgun.expensetrackerapp.presentation.screen.createreport

import androidx.compose.material3.SnackbarHostState

data class CreateReportScreenState(
    val reportName: String = "",
    val reportNameError: String? = null,
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
)
