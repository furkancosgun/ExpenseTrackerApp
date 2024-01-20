package com.furkancosgun.expensetrackerapp.data.datasource

import com.furkancosgun.expensetrackerapp.data.model.request.CreateCategoryRequest
import com.furkancosgun.expensetrackerapp.data.model.response.GetCategoriesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CategoryDataSource {
    @POST("category/create")
    suspend fun createCategory(@Body request: CreateCategoryRequest): Response<Unit>

    @POST("category/list")
    suspend fun getCategories(): Response<List<GetCategoriesResponse>>
}