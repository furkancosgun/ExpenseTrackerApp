package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.furkancosgun.expensetrackerapp.presentation.screen.home.HomeScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.home.HomeScreenState

class HomeScreenViewModel : ViewModel() {
    var state by mutableStateOf(HomeScreenState())
        private set
    
    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.SearchTextChanged -> {
                state = state.copy(searchText = state.searchText)
            }
        }
    }
}