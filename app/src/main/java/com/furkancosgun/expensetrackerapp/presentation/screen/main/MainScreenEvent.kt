package com.furkancosgun.expensetrackerapp.presentation.screen.main

sealed class MainScreenEvent {
    data object FabToggle : MainScreenEvent()
}