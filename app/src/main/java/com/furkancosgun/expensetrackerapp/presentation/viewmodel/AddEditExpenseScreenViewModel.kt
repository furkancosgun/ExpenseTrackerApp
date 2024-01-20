package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkancosgun.expensetrackerapp.data.model.request.CreateExpenseRequest
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitCategoryDataSource
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitExpenseDataSource
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitProjectDataSource
import com.furkancosgun.expensetrackerapp.data.repository.httpRequestHandler
import com.furkancosgun.expensetrackerapp.domain.model.KeyValue
import com.furkancosgun.expensetrackerapp.presentation.screen.addeditexpense.AddEditExpenseScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.addeditexpense.AddEditExpenseScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditExpenseScreenViewModel @SuppressLint("StaticFieldLeak") constructor(
    private val savedStateHandle: SavedStateHandle,
    private val context: Context,
    private val categoryDataSource: RetrofitCategoryDataSource,
    private val projectDataSource: RetrofitProjectDataSource,
    private val expenseDataSource: RetrofitExpenseDataSource
) : ViewModel() {
    var state by mutableStateOf(AddEditExpenseScreenState())
        private set

    private val eventChannel = Channel<AddEditExpenseScreenViewModelEvent>()
    val event = eventChannel.receiveAsFlow()

    init {
        val expenseId = savedStateHandle.get<String>("id")
        viewModelScope.launch {
            getCategories()
        }
        viewModelScope.launch {
            getReports()
        }
    }

    private suspend fun getCategories() {
        httpRequestHandler(
            request = {
                categoryDataSource.getCategories()
            }, onSuccess = {
                val categoryList = it.map { getCategoriesResponse ->
                    KeyValue(
                        getCategoriesResponse.categoryId,
                        getCategoriesResponse.categoryName
                    )
                }.toList()
                state = state.copy(categories = categoryList)
            }, onError = {
                println(it)
            }
        )
    }

    private suspend fun getReports() {
        httpRequestHandler(
            request = {
                projectDataSource.getProjects()
            }, onSuccess = {
                val reportList = it.map { getProjectResponse ->
                    KeyValue(
                        getProjectResponse.projectId,
                        getProjectResponse.projectName
                    )
                }.toList()
                state = state.copy(projects = reportList)
            }, onError = {
                println(it)
            }
        )
    }

    fun onEvent(event: AddEditExpenseScreenEvent) {
        when (event) {
            is AddEditExpenseScreenEvent.AmountChanged -> {
                state = state.copy(amount = event.amount)
            }

            is AddEditExpenseScreenEvent.CategoryChanged -> {
                state = state.copy(category = event.category.value, categoryId = event.category.key)
            }

            is AddEditExpenseScreenEvent.DateChanged -> {
                state = state.copy(date = event.date)
            }

            is AddEditExpenseScreenEvent.DescriptionChanged -> {
                state = state.copy(description = event.description)
            }

            is AddEditExpenseScreenEvent.MerchantNameChanged -> {
                state = state.copy(merchantName = event.merchantName)
            }

            is AddEditExpenseScreenEvent.VatChanged -> {
                state = state.copy(vat = event.vat)
            }

            is AddEditExpenseScreenEvent.Submit -> {
                viewModelScope.launch {
                    submitData()
                }
            }

            is AddEditExpenseScreenEvent.IncludeVatChanged -> {
                state = state.copy(includeVat = event.includeVat)
            }

            is AddEditExpenseScreenEvent.CreateCategory -> {
                state = state.copy(isOpenCategoryAlert = !state.isOpenCategoryAlert)
            }

            is AddEditExpenseScreenEvent.ReportChanged -> {
                state = state.copy(project = event.report.value, projectId = event.report.key)
            }
        }
    }


    private suspend fun submitData() {
        val request = CreateExpenseRequest(
            projectId = state.projectId,
            merchantName = state.merchantName,
            amount = state.amount,
            date = state.date,
            description = state.description,
            categoryId = state.categoryId,
            includeVat = state.includeVat,
            vat = state.vat
        )

        httpRequestHandler(
            request = {
                expenseDataSource.createExpense(request)
            }, onSuccess = {
                viewModelScope.launch {
                    eventChannel.send(AddEditExpenseScreenViewModelEvent.Success)
                }
            }, onError = {
                viewModelScope.launch {
                    eventChannel.send(AddEditExpenseScreenViewModelEvent.Error(it))
                }
            }
        )
    }

    sealed class AddEditExpenseScreenViewModelEvent {
        data object Success : AddEditExpenseScreenViewModelEvent()
        data class Error(val error: String) : AddEditExpenseScreenViewModelEvent()
    }
}