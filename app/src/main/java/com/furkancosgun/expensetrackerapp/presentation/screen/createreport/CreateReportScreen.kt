package com.furkancosgun.expensetrackerapp.presentation.screen.createreport

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.domain.usecase.ValidateReportNameUseCase
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UISpacing
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.CreateReportViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateReportScreen(navController: NavController,viewModel: CreateReportViewModel= koinViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(UIPadding.MEDIUM.size),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.create_expense_report),
            style = MaterialTheme.typography.titleLarge,
            color = PrimaryColor
        )
        Text(text = stringResource(R.string.create_a_report_to_easily_track_your_expenses))

        Spacer(modifier = Modifier.height(UISpacing.LARGE.size))

        AppOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = viewModel.state.reportName,
            onTextChanged = {
                viewModel.onEvent(CreateReportScreenEvent.ReportNameChanged(it))
            },
            icon = Icons.Default.FileOpen,
            label = stringResource(R.string.report_name),
            errorText = viewModel.state.reportNameError
        )

        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.create)
        ) {
            viewModel.onEvent(CreateReportScreenEvent.Submit)
        }
        AppButton(text = "asd") {
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CreateReportScreen_Preview() {
    ExpenseTrackerTheme {
        CreateReportScreen( rememberNavController(),
            CreateReportViewModel(ValidateReportNameUseCase(LocalContext.current))
        )
    }
}