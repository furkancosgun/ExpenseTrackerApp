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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateEmailUseCase
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.forgotpassword.ForgotPasswordScreenEvent
import com.furkancosgun.expensetrackerapp.presentation.ui.forgotpassword.ForgotPasswordTitle
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
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

                }

                is ForgotPasswordViewModel.ForgotPasswordViewModelEvent.Success -> {
                    navController.navigate(Screen.Auth.VerifyAccount.route)
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


@Preview(showSystemUi = true)
@Composable
fun ForgotPasswordScreen_Preview() {
    ExpenseTrackerTheme {
        ForgotPasswordScreen(
            rememberNavController(),
            ForgotPasswordViewModel(ValidateEmailUseCase(LocalContext.current))
        )
    }
}