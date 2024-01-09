package com.furkancosgun.expensetrackerapp.domain.usecase

import android.content.Context
import com.furkancosgun.expensetrackerapp.R

class ValidateLastNameUseCase(private val context: Context) {
    operator fun invoke(lastName: String): ValidationResult {
        if (lastName.isBlank()) {
            return ValidationResult(
                false,
                context.getString(R.string.the_last_name_can_t_be_blank),
            )
        }
        return ValidationResult(true)
    }
}