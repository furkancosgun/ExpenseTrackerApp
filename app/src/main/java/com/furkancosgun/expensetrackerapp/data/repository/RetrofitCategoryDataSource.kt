package com.furkancosgun.expensetrackerapp.data.repository

import com.furkancosgun.expensetrackerapp.data.common.RetrofitClient
import com.furkancosgun.expensetrackerapp.data.datasource.CategoryDataSource
import com.furkancosgun.expensetrackerapp.data.model.request.CreateCategoryRequest
import com.furkancosgun.expensetrackerapp.data.model.response.GetCategoriesResponse
import retrofit2.Response

class RetrofitCategoryDataSource : CategoryDataSource {
    private val categoryService = RetrofitClient.getCategoryService()
    override suspend fun createCategory(request: CreateCategoryRequest): Response<Unit> {
        return categoryService.createCategory(request)
    }

    override suspend fun getCategories(): Response<List<GetCategoriesResponse>> {
        return categoryService.getCategories()
    }
}