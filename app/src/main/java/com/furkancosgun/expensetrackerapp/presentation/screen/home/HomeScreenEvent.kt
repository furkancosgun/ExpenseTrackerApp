package com.furkancosgun.expensetrackerapp.presentation.screen.home

sealed class HomeScreenEvent {
    data class SearchTextChanged(val searchText: String) : HomeScreenEvent()
}
