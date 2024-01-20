package com.furkancosgun.expensetrackerapp.di

import android.content.Context
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitAuthDataSource
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitCategoryDataSource
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitExpenseDataSource
import com.furkancosgun.expensetrackerapp.data.repository.RetrofitProjectDataSource
import com.furkancosgun.expensetrackerapp.data.repository.UserSessionManagerRepository
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateCategoryUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateEmailUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateFirstNameUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateLastNameUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateOtpCodeUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidatePasswordUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateRepeatedPasswordUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateReportNameUseCase
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.AddEditExpenseScreenViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.CreateCategoryViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.CreateReportViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.ForgotPasswordViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.HomeScreenViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.LoginViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.MainScreenViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.RegisterViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.ResetPasswordViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.SettingsScreenViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.VerifyAccountViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.VerifyResetPasswordViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    //Validation
    single { ValidateFirstNameUseCase(get()) }
    single { ValidateLastNameUseCase(get()) }
    single { ValidateEmailUseCase(get()) }
    single { ValidatePasswordUseCase(get()) }
    single { ValidateRepeatedPasswordUseCase(get()) }
    single { ValidateOtpCodeUseCase(get()) }
    single { ValidateReportNameUseCase(get()) }
    single { ValidateCategoryUseCase(get()) }

    //ViewModel
    viewModel { LoginViewModel(get(), get(), get(), get()) }
    viewModel { RegisterViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { ForgotPasswordViewModel(get(), get()) }
    viewModel { VerifyAccountViewModel(get(), get(), get()) }
    viewModel { VerifyResetPasswordViewModel(get(), get(), get()) }
    viewModel { ResetPasswordViewModel(get(), get(), get(), get()) }
    viewModel { CreateReportViewModel(get(), get()) }
    viewModel { MainScreenViewModel() }
    viewModel { HomeScreenViewModel(get()) }
    viewModel { AddEditExpenseScreenViewModel(get(), get(), get(), get(), get()) }
    viewModel { CreateCategoryViewModel(get(), get()) }
    viewModel { SettingsScreenViewModel(get()) }

    //Repositories
    single { RetrofitAuthDataSource() }
    single { RetrofitProjectDataSource() }
    single { RetrofitCategoryDataSource() }
    single { RetrofitExpenseDataSource() }
    single {
        UserSessionManagerRepository(
            sharedPreferences = androidContext().getSharedPreferences(
                "default", Context.MODE_PRIVATE
            )
        )
    }
}