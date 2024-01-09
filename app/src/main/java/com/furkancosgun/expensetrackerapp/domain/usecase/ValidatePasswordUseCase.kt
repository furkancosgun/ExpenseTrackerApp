package com.furkancosgun.expensetrackerapp.domain.usecase

import android.content.Context
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.domain.common.AppConstants

class ValidatePasswordUseCase(private val context: Context) {
    operator fun invoke(password: String): ValidationResult {
        if (password.length < AppConstants.MIN_PASSWORD_LENGTH) {
            return ValidationResult(
                false,
                context.getString(
                    R.string.password_must_be_greater_than,
                    AppConstants.MIN_PASSWORD_LENGTH
                )
            )
        }
        val containsLettersAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }

        if (!containsLettersAndDigits) {
            return ValidationResult(
                false,
                context.getString(R.string.password_need_contains_one_letter_and_digit)
            )
        }
        return ValidationResult(true)
    }
}