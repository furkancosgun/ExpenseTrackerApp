package com.furkancosgun.expensetrackerapp.data.repository

import com.furkancosgun.expensetrackerapp.data.model.response.ErrorResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


suspend fun <T : Any> httpRequestHandler(
    request: suspend () -> Response<T>,
    onSuccess: (T) -> Unit,
    onError: (String) -> Unit,
) {
    try {
        val response = request.invoke()
        if (response.isSuccessful && response.code() < 400) {
            response.body()?.let {
                onSuccess.invoke(it)
            }
        } else {
            onError.invoke(
                ErrorResponse.fromResponse(response.errorBody()).errorDescription
            )
        }
    } catch (e: IOException) {
        onError.invoke("Network error")
    } catch (e: HttpException) {
        onError.invoke("HTTP error: ${e.code()}")
    } catch (e: Exception) {
        onError.invoke("An unexpected error occurred")
    }
}