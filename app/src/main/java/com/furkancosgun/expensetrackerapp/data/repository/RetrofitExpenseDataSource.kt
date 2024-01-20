package com.furkancosgun.expensetrackerapp.data.repository

import com.furkancosgun.expensetrackerapp.data.common.RetrofitClient
import com.furkancosgun.expensetrackerapp.data.datasource.ExpenseDataSource
import com.furkancosgun.expensetrackerapp.data.model.request.CreateExpenseRequest
import retrofit2.Response

class RetrofitExpenseDataSource : ExpenseDataSource {
    private val expenseService = RetrofitClient.getExpenseService()
    override suspend fun createExpense(request: CreateExpenseRequest): Response<Unit> {
        return expenseService.createExpense(request)
    }
}