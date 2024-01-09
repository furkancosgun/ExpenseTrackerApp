package com.furkancosgun.expensetrackerapp.domain.usecase

import android.content.Context
import com.furkancosgun.expensetrackerapp.R

class ValidateRepeatedPasswordUseCase(private val context: Context) {
    operator fun invoke(password: String, repeatedPassword: String): ValidationResult {
        if (password != repeatedPassword) {
            return ValidationResult(false, context.getString(R.string.passwords_don_t_match))
        }
        return ValidationResult(true, null)
    }
}