package com.furkancosgun.expensetrackerapp.domain.usecase

import android.content.Context
import com.furkancosgun.expensetrackerapp.R

class ValidateCategoryUseCase(
    private val context: Context
) {
    operator fun invoke(category: String): ValidationResult {
        if (category.isEmpty()) {
            return ValidationResult(false, context.getString(R.string.the_category_can_t_be_empty))
        }
        return ValidationResult(true)
    }
}