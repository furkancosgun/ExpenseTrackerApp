package com.furkancosgun.expensetrackerapp.presentation.screen.addeditexpense

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.screen.createcategory.CreateCategoryAlert
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedMenuField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppTextButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UISpacing
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.AddEditExpenseScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddEditExpenseScreen(
    navController: NavController,
    viewModel: AddEditExpenseScreenViewModel = koinViewModel()
) {


    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.event.collect {
            when (it) {
                is AddEditExpenseScreenViewModel.AddEditExpenseScreenViewModelEvent.Error -> {
                    viewModel.state.snackBarHostState.showSnackbar(it.error)
                }

                is AddEditExpenseScreenViewModel.AddEditExpenseScreenViewModelEvent.Success -> {
                    navController.navigate(Screen.App.Base.route) {
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
    ) { it ->
        if (viewModel.state.isOpenCategoryAlert) {
            CreateCategoryAlert(anyButtonClicked = {
                viewModel.onEvent(AddEditExpenseScreenEvent.CreateCategory)
            })
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(UIPadding.MEDIUM.size)
        ) {
            Text(
                text = stringResource(R.string.create_expense),
                style = MaterialTheme.typography.titleLarge,
                color = PrimaryColor
            )
            Text(text = stringResource(R.string.you_can_record_your_expenses_in_detail))

            Spacer(modifier = Modifier.height(UISpacing.LARGE.size))

            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.merchant_name),
                text = viewModel.state.merchantName,
                onTextChanged = {
                    viewModel.onEvent(AddEditExpenseScreenEvent.MerchantNameChanged(it))
                },
                icon = Icons.Default.PushPin,
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.amount),
                text = viewModel.state.amount.toString(),
                onTextChanged = {
                    viewModel.onEvent(AddEditExpenseScreenEvent.AmountChanged(it.toDouble()))
                },
                icon = Icons.Default.AttachMoney,
                keyboardType = KeyboardType.Decimal,
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string._12_12_2012),
                text = viewModel.state.date,
                onTextChanged = {
                    viewModel.onEvent(AddEditExpenseScreenEvent.DateChanged(it))
                },
                icon = Icons.Default.DateRange,
                keyboardType = KeyboardType.Number,
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.description),
                text = viewModel.state.description,
                onTextChanged = {
                    viewModel.onEvent(AddEditExpenseScreenEvent.DescriptionChanged(it))
                },
                icon = Icons.Default.Description
            )
            AppOutlinedMenuField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.category),
                value = viewModel.state.category,
                dropDownList = viewModel.state.categories,
            ) {
                viewModel.onEvent(AddEditExpenseScreenEvent.CategoryChanged(it))
            }
            AppTextButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.create_category)
            ) {
                viewModel.onEvent(AddEditExpenseScreenEvent.CreateCategory)
            }
            AppOutlinedMenuField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(id = R.string.report),
                value = viewModel.state.project,
                dropDownList = viewModel.state.projects,
            ) {
                viewModel.onEvent(AddEditExpenseScreenEvent.ReportChanged(it))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(UISpacing.MEDIUM.size)
            ) {
                Switch(checked = viewModel.state.includeVat, onCheckedChange = {
                    viewModel.onEvent(AddEditExpenseScreenEvent.IncludeVatChanged(it))
                })
                Text(text = stringResource(R.string.include_vat_tax))
            }
            AnimatedVisibility(visible = viewModel.state.includeVat) {
                AppOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(R.string.vat_tax),
                    text = viewModel.state.vat.toString(),
                    onTextChanged = {
                        viewModel.onEvent(AddEditExpenseScreenEvent.VatChanged(it.toDouble()))
                    },
                    icon = Icons.Default.AttachMoney,
                    keyboardType = KeyboardType.Decimal
                )
            }
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.save_expense)
            ) {
                viewModel.onEvent(AddEditExpenseScreenEvent.Submit)
            }
        }
    }
}
