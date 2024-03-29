package com.furkancosgun.expensetrackerapp.presentation.screen.forgotpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.forgotpassword.ForgotPasswordTitle
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.ForgotPasswordViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: ForgotPasswordViewModel = koinViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.event.collect {
            when (it) {
                is ForgotPasswordViewModel.ForgotPasswordViewModelEvent.Error -> {
                    viewModel.state.snackBarHostState.showSnackbar(it.error)
                }

                is ForgotPasswordViewModel.ForgotPasswordViewModelEvent.Success -> {
                    navController.navigate(
                        Screen.Auth.VerifyResetPassword.route.replace(
                            oldValue = "{email}",
                            newValue = viewModel.state.email
                        )
                    )
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
            verticalArrangement = Arrangement.Center,
        ) {
            ForgotPasswordTitle()
            Spacer(modifier = Modifier.height(UIPadding.LARGE.size))
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(id = R.string.email),
                text = viewModel.state.email,
                onTextChanged = {
                    viewModel.onEvent(ForgotPasswordScreenEvent.EmailChanged(it))
                },
                icon = Icons.Default.Email,
                errorText = viewModel.state.emailError
            )
            AppButton(modifier = Modifier.fillMaxWidth(), text = stringResource(R.string.send)) {
                viewModel.onEvent(ForgotPasswordScreenEvent.Submit)
            }
        }
    }
}

