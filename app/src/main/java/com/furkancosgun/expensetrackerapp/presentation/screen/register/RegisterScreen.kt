package com.furkancosgun.expensetrackerapp.presentation.screen.register

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
import androidx.compose.material.icons.filled.Person
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
import com.furkancosgun.expensetrackerapp.presentation.ui.register.RegisterScreenTitle
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.RegisterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = koinViewModel()) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.event.collect {
            when (it) {
                is RegisterViewModel.RegisterViewModelEvent.Error -> {
                    viewModel.state.snackBarHostState.showSnackbar(it.error)
                }

                is RegisterViewModel.RegisterViewModelEvent.Success -> {
                    navController.navigate(
                        Screen.Auth.VerifyAccount.route.replace(
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
            SnackbarHost(viewModel.state.snackBarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(UIPadding.MEDIUM.size),
            verticalArrangement = Arrangement.Center,
        ) {
            RegisterScreenTitle()

            Spacer(modifier = Modifier.height(UIPadding.LARGE.size))

            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.state.firstName,
                label = stringResource(R.string.first_name),
                onTextChanged = {
                    viewModel.onEvent(RegisterScreenEvent.FirstNameChanged(it))
                },
                icon = Icons.Default.Person,
                errorText = viewModel.state.firstNameError
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.state.lastName,
                label = stringResource(R.string.last_name),
                onTextChanged = {
                    viewModel.onEvent(RegisterScreenEvent.LastNameChanged(it))
                },
                icon = Icons.Default.Person,
                errorText = viewModel.state.lastNameError
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.state.email,
                label = stringResource(id = R.string.email),
                onTextChanged = {
                    viewModel.onEvent(RegisterScreenEvent.EmailChanged(it))
                },
                icon = Icons.Default.Email,
                errorText = viewModel.state.emailError
            )
            AppOutlinedPasswordField(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.state.password,
                label = stringResource(id = R.string.password),
                onTextChanged = {
                    viewModel.onEvent(RegisterScreenEvent.PasswordChanged(it))
                },
                icon = Icons.Default.Password,
                errorText = viewModel.state.passwordError
            )
            AppOutlinedPasswordField(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.state.repeatedPassword,
                label = stringResource(R.string.repeat_password),
                onTextChanged = {
                    viewModel.onEvent(RegisterScreenEvent.RepeatedPasswordChanged(it))
                },
                icon = Icons.Default.Password,
                errorText = viewModel.state.repeatedPasswordError
            )
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.register)
            ) {
                viewModel.onEvent(RegisterScreenEvent.Submit)
            }
            AppTextButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.already_have_a_account),
                textAlign = TextAlign.Center
            ) {
                navController.popBackStack()
            }
        }
    }
}