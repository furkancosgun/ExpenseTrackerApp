package com.furkancosgun.expensetrackerapp.presentation.screen.createreport

sealed class CreateReportScreenEvent {
    data class ReportNameChanged(val reportName: String) : CreateReportScreenEvent()
    data object Submit : CreateReportScreenEvent()
}
