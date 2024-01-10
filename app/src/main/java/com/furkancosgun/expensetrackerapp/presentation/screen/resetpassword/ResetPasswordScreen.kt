package com.furkancosgun.expensetrackerapp.presentation.screen.resetpassword

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidatePasswordUseCase
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateRepeatedPasswordUseCase
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UISpacing
import com.furkancosgun.expensetrackerapp.presentation.ui.resetpassword.ResetPasswordScreenTitle
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.ResetPasswordViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    viewModel: ResetPasswordViewModel = koinViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.event.collect {
            when (it) {
                is ResetPasswordViewModel.ResetPasswordViewModelEvent.Error -> TODO()
                is ResetPasswordViewModel.ResetPasswordViewModelEvent.Success -> {
                    navController.navigate(Screen.Auth.Login.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(UIPadding.MEDIUM.size),
        verticalArrangement = Arrangement.Center
    ) {
        ResetPasswordScreenTitle()
        Spacer(modifier = Modifier.height(UISpacing.LARGE.size))

        AppOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = viewModel.state.password,
            onTextChanged = {
                viewModel.onEvent(ResetPasswordScreenEvent.PasswordChanged(it))
            },
            label = stringResource(id = R.string.password),
            icon = Icons.Default.Password,
            errorText = viewModel.state.passwordError
        )
        AppOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = viewModel.state.repeatedPassword,
            onTextChanged = {
                viewModel.onEvent(ResetPasswordScreenEvent.RepeatedPasswordChanged(it))
            },
            label = stringResource(id = R.string.repeat_password),
            icon = Icons.Default.Password,
            errorText = viewModel.state.repeatedPasswordError
        )
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.reset_password)
        ) {
            viewModel.onEvent(ResetPasswordScreenEvent.Submit)
        }
    }
    BackHandler {
        navController.navigate(Screen.Auth.Login.route){
            popUpTo(navController.graph.id){
                inclusive = true
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ResetPasswordScreen_Preview() {
    ExpenseTrackerTheme {
        ResetPasswordScreen(
            navController = rememberNavController(), viewModel = ResetPasswordViewModel(
                ValidatePasswordUseCase(LocalContext.current),
                ValidateRepeatedPasswordUseCase(LocalContext.current),
            )
        )
    }
}