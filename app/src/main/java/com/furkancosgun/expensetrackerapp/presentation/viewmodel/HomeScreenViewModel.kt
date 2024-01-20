package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitProjectDataSource
import com.furkancosgun.expensetrackerapp.data.repository.httpRequestHandler
import com.furkancosgun.expensetrackerapp.presentation.screen.home.HomeScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.home.HomeScreenState
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val reportDataSource: RetrofitProjectDataSource
) : ViewModel() {
    var state by mutableStateOf(HomeScreenState())
        private set

    init {
        viewModelScope.launch {
            getUserReports()
        }
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.SearchTextChanged -> {
                state = state.copy(searchText = state.searchText)
            }
        }
    }

    private suspend fun getUserReports() {
        httpRequestHandler(request = {
            reportDataSource.getProjectReport()
        }, onSuccess = { response ->
            state = state.copy(
                createdExpenseReportCount = response.count(),
                recentExpenseReports = response
            )
        }, onError = { _ -> })
    }

}