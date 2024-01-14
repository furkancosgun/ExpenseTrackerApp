package com.furkancosgun.expensetrackerapp.data.repository

import android.content.SharedPreferences
import com.furkancosgun.expensetrackerapp.domain.manager.UserSessionManager
import com.furkancosgun.expensetrackerapp.domain.model.UserSessionModel

class UserSessionManagerRepository(private val sharedPreferences: SharedPreferences) :
    UserSessionManager {
    override fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    override fun loginUser(sessionModel: UserSessionModel) {
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
        sharedPreferences.edit().putString("firstname", sessionModel.firstName).apply()
        sharedPreferences.edit().putString("lastname", sessionModel.lastName).apply()
        sharedPreferences.edit().putString("email", sessionModel.email).apply()
        sharedPreferences.edit().putString("token", sessionModel.token).apply()
    }

    override fun logoutUser() {
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
        sharedPreferences.edit().remove("firstname").apply()
        sharedPreferences.edit().remove("lastname").apply()
        sharedPreferences.edit().remove("email").apply()
        sharedPreferences.edit().remove("token").apply()
        userSession = UserSessionModel()
    }

    override fun getUser(): UserSessionModel {
        if (userSession.token.isEmpty()) {
            val firstName = sharedPreferences.getString("firstname", "") ?: ""
            val lastName = sharedPreferences.getString("lastname", "") ?: ""
            val email = sharedPreferences.getString("email", "") ?: ""
            val token = sharedPreferences.getString("token", "") ?: ""
            userSession = UserSessionModel(firstName, lastName, email, token)
        }
        return userSession
    }

    companion object {
        var userSession = UserSessionModel()
    }
}
