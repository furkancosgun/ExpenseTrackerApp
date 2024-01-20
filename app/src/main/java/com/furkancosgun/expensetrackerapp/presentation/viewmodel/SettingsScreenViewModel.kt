package com.furkancosgun.expensetrackerapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.furkancosgun.expensetrackerapp.data.repository.UserSessionManagerRepository

class SettingsScreenViewModel(
    private val userSessionManager: UserSessionManagerRepository
) : ViewModel() {

    fun onLogOut() {
        userSessionManager.logoutUser()
    }
}