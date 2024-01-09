package com.furkancosgun.expensetrackerapp.domain.usecase

import android.content.Context
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.domain.common.AppConstants

class ValidateOtpCodeUseCase(private val context: Context) {
    operator fun invoke(otpCode: String): ValidationResult {
        if (otpCode.isBlank()) {
            return ValidationResult(false, context.getString(R.string.the_otp_can_t_be_blank))
        }
        if (otpCode.length < AppConstants.OTP_CODE_LENGTH) {
            return ValidationResult(
                false,
                context.getString(
                    R.string.the_otp_code_must_be_d_characters_long,
                    AppConstants.OTP_CODE_LENGTH
                )
            )
        }
        return ValidationResult(true)
    }
}