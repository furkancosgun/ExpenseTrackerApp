package com.furkancosgun.expensetrackerapp.domain.usecase

import android.content.Context
import android.util.Patterns
import com.furkancosgun.expensetrackerapp.R

class ValidateEmailUseCase(private val context: Context) {

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(false, context.getString(R.string.email_cant_be_blank))
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(false, context.getString(R.string.email_must_be_correct_format))
        }
        return ValidationResult(true)
    }
}