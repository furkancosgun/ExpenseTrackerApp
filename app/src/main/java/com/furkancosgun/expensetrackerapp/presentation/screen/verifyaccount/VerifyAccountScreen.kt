package com.furkancosgun.expensetrackerapp.presentation.screen.verifyaccount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.domain.common.AppConstants
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateOtpCodeUseCase
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UISpacing
import com.furkancosgun.expensetrackerapp.presentation.ui.otp.OtpTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
import com.furkancosgun.expensetrackerapp.presentation.ui.verifyaccount.VerifyAccountScreenTitle
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.VerifyAccountViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.scope.ScopeID

@Composable
fun VerifyAccountScreen(
    navController: NavController,
    email: String,
    next: String,
    viewModel: VerifyAccountViewModel = koinViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context){
        viewModel.event.collect{
            when(it){
                is VerifyAccountViewModel.VerifyAccountViewModelEvent.Error -> TODO()
                is VerifyAccountViewModel.VerifyAccountViewModelEvent.Success -> {
                    navController.navigate(next)
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

@Preview(showSystemUi = true)
@Composable
fun VerifyAccountScreen_Preview() {
    ExpenseTrackerTheme {
        VerifyAccountScreen(
            navController = rememberNavController(),
            "furkan51cosgun@gmail.com",
            Screen.Auth.ResetPassword.route,
            viewModel = VerifyAccountViewModel(ValidateOtpCodeUseCase(LocalContext.current)),
        )
    }
}
