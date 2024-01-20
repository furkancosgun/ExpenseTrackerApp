package com.furkancosgun.expensetrackerapp.data.datasource

import com.furkancosgun.expensetrackerapp.data.model.request.CreateExpenseRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ExpenseDataSource {
    @POST("expense/create")
    suspend fun createExpense(@Body request: CreateExpenseRequest): Response<Unit>
}