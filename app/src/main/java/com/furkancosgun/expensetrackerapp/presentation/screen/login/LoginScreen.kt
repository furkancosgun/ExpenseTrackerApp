package com.furkancosgun.expensetrackerapp.presentation.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedPasswordField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppTextButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.login.LoginScreenTitle
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = koinViewModel()) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.event.collect {
            when (it) {
                is LoginViewModel.LoginViewModelEvent.Error -> {
                    viewModel.state.snackBarHostState.showSnackbar(
                        message = it.error
                    )
                }

                is LoginViewModel.LoginViewModelEvent.Success -> {
                    navController.navigate(Screen.App.route)
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
            LoginScreenTitle()
            Spacer(modifier = Modifier.height(UIPadding.LARGE.size))
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.state.email,
                label = stringResource(id = R.string.email),
                onTextChanged = {
                    viewModel.onEvent(LoginScreenEvent.EmailChanged(it))
                },
                icon = Icons.Default.Email,
                errorText = viewModel.state.emailError
            )
            AppOutlinedPasswordField(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.state.password,
                label = stringResource(id = R.string.password),
                onTextChanged = {
                    viewModel.onEvent(LoginScreenEvent.PasswordChanged(it))
                },
                icon = Icons.Default.Password,
                errorText = viewModel.state.passwordError
            )
            AppTextButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.forgot_your_password)
            ) {
                navController.navigate(Screen.Auth.ForgotPassword.route)
            }
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.login)
            ) {
                viewModel.onEvent(LoginScreenEvent.Submit)
            }

            AppTextButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.don_t_have_a_account),
                textAlign = TextAlign.Center
            ) {
                navController.navigate(Screen.Auth.Register.route)
            }
        }
    }
}
