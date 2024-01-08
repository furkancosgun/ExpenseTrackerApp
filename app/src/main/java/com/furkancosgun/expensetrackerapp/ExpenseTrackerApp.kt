package com.furkancosgun.expensetrackerapp

import android.app.Application
import com.furkancosgun.expensetrackerapp.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ExpenseTrackerApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ExpenseTrackerApp)
            modules(AppModule)
        }
    }
}