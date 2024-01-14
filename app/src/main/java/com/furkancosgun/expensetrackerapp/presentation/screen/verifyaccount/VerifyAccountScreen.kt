package com.furkancosgun.expensetrackerapp.presentation.screen.verifyaccount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.domain.common.AppConstants
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UISpacing
import com.furkancosgun.expensetrackerapp.presentation.ui.otp.OtpTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.verifyaccount.VerifyAccountScreenTitle
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.VerifyAccountViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun VerifyAccountScreen(
    navController: NavController,
    email: String,
    viewModel: VerifyAccountViewModel = koinViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.event.collect {
            when (it) {
                is VerifyAccountViewModel.VerifyAccountViewModelEvent.Error -> {
                    viewModel.state.snackBarHostState.showSnackbar(it.error)
                }

                is VerifyAccountViewModel.VerifyAccountViewModelEvent.Success -> {
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
            VerifyAccountScreenTitle(email = email)
            Spacer(modifier = Modifier.height(UIPadding.LARGE.size))
            OtpTextField(
                modifier = Modifier.fillMaxWidth(),
                otpText = viewModel.state.otpCode,
                onOtpTextChange = {
                    viewModel.onEvent(VerifyAccountScreenEvent.OtpCodeChanged(it))
                },
                otpCount = AppConstants.OTP_CODE_LENGTH,
                errorText = viewModel.state.otpCodeError
            )
            Spacer(modifier = Modifier.height(UISpacing.MEDIUM.size))
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.verify)
            ) {
                viewModel.onEvent(VerifyAccountScreenEvent.Submit)
            }
        }
    }
}
