package com.furkancosgun.expensetrackerapp.domain.usecase

import android.content.Context
import com.furkancosgun.expensetrackerapp.R

class ValidateReportNameUseCase(
    private val context: Context
) {
    operator fun invoke(reportName: String): ValidationResult {
        if (reportName.isBlank()) {
            return ValidationResult(
                false,
                context.getString(R.string.the_report_name_can_t_be_blank)
            )
        }
        return ValidationResult(true)
    }
}