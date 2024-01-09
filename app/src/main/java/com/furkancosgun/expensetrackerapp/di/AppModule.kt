package com.furkancosgun.expensetrackerapp.di

import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateEmailUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateFirstNameUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateLastNameUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidatePasswordUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateRepeatedPasswordUseCase
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.LoginViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val AppModule = module {
    single { ValidateEmailUseCase(get()) }
    single { ValidatePasswordUseCase(get()) }
    single { ValidateRepeatedPasswordUseCase(get()) }
    single { ValidateFirstNameUseCase(get()) }
    single { ValidateLastNameUseCase(get()) }
    viewModel { LoginViewModel(get(), get()) }
}