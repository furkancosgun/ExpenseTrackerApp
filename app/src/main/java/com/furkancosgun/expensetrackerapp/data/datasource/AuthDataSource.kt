package com.furkancosgun.expensetrackerapp.data.datasource

import com.furkancosgun.expensetrackerapp.data.model.request.ForgotPasswordRequest
import com.furkancosgun.expensetrackerapp.data.model.request.LoginRequest
import com.furkancosgun.expensetrackerapp.data.model.request.RegisterRequest
import com.furkancosgun.expensetrackerapp.data.model.request.ResetPasswordRequest
import com.furkancosgun.expensetrackerapp.data.model.request.VerifyAccountRequest
import com.furkancosgun.expensetrackerapp.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthDataSource {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<Unit>

    @POST("auth/verify-account")
    suspend fun verifyAccount(@Body verifyAccountRequest: VerifyAccountRequest): Response<Unit>

    @POST("auth/forgot-password")
    suspend fun forgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest): Response<Unit>

    @POST("auth/reset-password")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Response<Unit>
}