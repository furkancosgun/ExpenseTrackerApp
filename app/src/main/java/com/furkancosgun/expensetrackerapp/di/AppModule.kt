package com.furkancosgun.expensetrackerapp.di

import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateEmailUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateFirstNameUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateLastNameUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateOtpCodeUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidatePasswordUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateRepeatedPasswordUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateReportNameUseCase
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.CreateReportViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.ForgotPasswordViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.HomeScreenViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.LoginViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.MainScreenViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.RegisterViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.ResetPasswordViewModel
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.VerifyAccountViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single { ValidateFirstNameUseCase(get()) }
    single { ValidateLastNameUseCase(get()) }
    single { ValidateEmailUseCase(get()) }
    single { ValidatePasswordUseCase(get()) }
    single { ValidateRepeatedPasswordUseCase(get()) }
    single { ValidateOtpCodeUseCase(get()) }
    single { ValidateReportNameUseCase(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get(), get(), get(), get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { VerifyAccountViewModel(get()) }
    viewModel { ResetPasswordViewModel(get(), get()) }
    viewModel { CreateReportViewModel(get()) }
    viewModel { MainScreenViewModel() }
    viewModel { HomeScreenViewModel() }
}