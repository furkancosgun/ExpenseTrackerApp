package com.furkancosgun.expensetrackerapp.data.repository

import com.furkancosgun.expensetrackerapp.data.common.RetrofitClient
import com.furkancosgun.expensetrackerapp.data.datasource.AuthDataSource
import com.furkancosgun.expensetrackerapp.data.model.request.ForgotPasswordRequest
import com.furkancosgun.expensetrackerapp.data.model.request.LoginRequest
import com.furkancosgun.expensetrackerapp.data.model.request.RegisterRequest
import com.furkancosgun.expensetrackerapp.data.model.request.ResetPasswordRequest
import com.furkancosgun.expensetrackerapp.data.model.request.VerifyAccountRequest
import com.furkancosgun.expensetrackerapp.data.model.response.LoginResponse
import retrofit2.Response

class RetrofitAuthDataSource : AuthDataSource {
    private val authService = RetrofitClient.getAuthService()
    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return authService.login(loginRequest)
    }

    override suspend fun register(registerRequest: RegisterRequest): Response<Unit> {
        return authService.register(registerRequest)
    }

    override suspend fun verifyAccount(verifyAccountRequest: VerifyAccountRequest): Response<Unit> {
        return authService.verifyAccount(verifyAccountRequest)
    }

    override suspend fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest): Response<Unit> {
        return authService.forgotPassword(forgotPasswordRequest)
    }

    override suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Response<Unit> {
        return authService.resetPassword(resetPasswordRequest)
    }
}