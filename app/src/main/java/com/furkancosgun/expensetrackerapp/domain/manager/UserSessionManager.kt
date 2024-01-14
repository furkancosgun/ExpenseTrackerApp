package com.furkancosgun.expensetrackerapp.domain.manager

import com.furkancosgun.expensetrackerapp.domain.model.UserSessionModel

interface UserSessionManager {
    fun isLoggedIn(): Boolean
    fun loginUser(sessionModel: UserSessionModel)
    fun logoutUser()
    fun getUser(): UserSessionModel
}
