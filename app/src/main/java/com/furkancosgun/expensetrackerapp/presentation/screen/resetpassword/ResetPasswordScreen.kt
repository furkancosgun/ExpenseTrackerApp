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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedPasswordField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UISpacing
import com.furkancosgun.expensetrackerapp.presentation.ui.resetpassword.ResetPasswordScreenTitle
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.ResetPasswordViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.component.getScopeName

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    viewModel: ResetPasswordViewModel = koinViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.event.collect {
            when (it) {
                is ResetPasswordViewModel.ResetPasswordViewModelEvent.Error -> {
                    viewModel.state.snackBarHostState.showSnackbar(it.error)
                }

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
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = viewModel.state.snackBarHostState)
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(UIPadding.MEDIUM.size),
            verticalArrangement = Arrangement.Center
        ) {
            ResetPasswordScreenTitle()
            Spacer(modifier = Modifier.height(UISpacing.LARGE.size))

            AppOutlinedPasswordField(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.state.password,
                onTextChanged = {
                    viewModel.onEvent(ResetPasswordScreenEvent.PasswordChanged(it))
                },
                label = stringResource(id = R.string.password),
                icon = Icons.Default.Password,
                errorText = viewModel.state.passwordError
            )
            AppOutlinedPasswordField(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.state.repeatedPassword,
                onTextChanged = {
                    viewModel.onEvent(ResetPasswordScreenEvent.RepeatedPasswordChanged(it))
                },
                label = stringResource(id = R.string.repeat_password),
                icon = Icons.Default.Password,
                errorText = viewModel.state.repeatedPasswordError,
            )
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.reset_password)
            ) {
                viewModel.onEvent(ResetPasswordScreenEvent.Submit)
            }
        }
    }
    BackHandler {
        navController.navigate(Screen.Auth.Login.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }
}