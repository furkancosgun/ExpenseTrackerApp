package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.furkancosgun.expensetrackerapp.presentation.screen.main.MainScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.screen.main.MainScreenState

class MainScreenViewModel : ViewModel() {
    var state by mutableStateOf(MainScreenState())
        private set

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.FabToggle -> {
                state = state.copy(isFabOpen = !state.isFabOpen)
            }
        }
    }
}