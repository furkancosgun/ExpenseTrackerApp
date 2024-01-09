package com.furkancosgun.expensetrackerapp.domain.usecase

import android.content.Context
import com.furkancosgun.expensetrackerapp.R

class ValidateFirstNameUseCase(private val context: Context) {
    operator fun invoke(firstName: String): ValidationResult {
        if (firstName.isBlank()) {
            return ValidationResult(
                false,
                context.getString(R.string.the_first_name_can_t_be_blank)
            )
        }
        return ValidationResult(true)
    }
}