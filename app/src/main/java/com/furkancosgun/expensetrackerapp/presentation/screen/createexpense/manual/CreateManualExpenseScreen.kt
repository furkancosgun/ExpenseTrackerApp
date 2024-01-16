package com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.manual

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.screen.createcategory.CreateCategoryAlert
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedMenuField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppTextButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UISpacing
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.CreateManualExpenseScreenViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateManualExpenseScreen(
    navController: NavController,
    viewModel: CreateManualExpenseScreenViewModel = koinViewModel()
) {

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            it?.let {
                viewModel.onEvent(CreateManualExpenseScreenEvent.UploadImage(it))
            }
        }

    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.event.collect {
            when (it) {
                is CreateManualExpenseScreenViewModel.CreateManualExpenseScreenViewModelEvent.Error -> {
                    viewModel.state.snackBarHostState.showSnackbar(it.error)
                }

                is CreateManualExpenseScreenViewModel.CreateManualExpenseScreenViewModelEvent.Success -> {
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
                viewModel.onEvent(CreateManualExpenseScreenEvent.CreateCategory)
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
                text = stringResource(R.string.create_expense_with_manual),
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
                    viewModel.onEvent(CreateManualExpenseScreenEvent.MerchantNameChanged(it))
                },
                icon = Icons.Default.PushPin,
                errorText = viewModel.state.merchantNameError
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.amount),
                text = viewModel.state.amount.toString(),
                onTextChanged = {
                    viewModel.onEvent(CreateManualExpenseScreenEvent.AmountChanged(it.toDouble()))
                },
                icon = Icons.Default.AttachMoney,
                keyboardType = KeyboardType.Decimal,
                errorText = viewModel.state.amountError
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string._12_12_2012),
                text = viewModel.state.date,
                onTextChanged = {
                    viewModel.onEvent(CreateManualExpenseScreenEvent.DateChanged(it))
                },
                icon = Icons.Default.DateRange,
                keyboardType = KeyboardType.Number,
                errorText = viewModel.state.dateError
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.description),
                text = viewModel.state.description,
                onTextChanged = {
                    viewModel.onEvent(CreateManualExpenseScreenEvent.DescriptionChanged(it))
                },
                icon = Icons.Default.Description
            )
            AppOutlinedMenuField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.category),
            )
            AppTextButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.create_category)
            ) {
                viewModel.onEvent(CreateManualExpenseScreenEvent.CreateCategory)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(UISpacing.MEDIUM.size)
            ) {
                Switch(checked = viewModel.state.includeVat, onCheckedChange = {
                    viewModel.onEvent(CreateManualExpenseScreenEvent.IncludeVatChanged(it))
                })
                Text(text = stringResource(R.string.include_vat_tax))
            }
            AnimatedVisibility(visible = viewModel.state.includeVat) {
                AppOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(R.string.vat_tax),
                    text = viewModel.state.vat.toString(),
                    onTextChanged = {
                        viewModel.onEvent(CreateManualExpenseScreenEvent.VatChanged(it.toDouble()))
                    },
                    icon = Icons.Default.AttachMoney,
                    keyboardType = KeyboardType.Decimal
                )
            }

            AnimatedVisibility(visible = viewModel.state.uploadedImage != null) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(400.dp),
                    painter = rememberAsyncImagePainter(model = viewModel.state.uploadedImage),
                    contentDescription = stringResource(R.string.expense_photo),
                    contentScale = ContentScale.FillHeight
                )
            }
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.upload_image)
            ) {
                galleryLauncher.launch("image/*")
            }

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.save_expense)
            ) {
                viewModel.onEvent(CreateManualExpenseScreenEvent.Submit)
            }
        }
    }
}

@Preview
@Composable
fun CreateManualExpenseScreen_Preview() {
    ExpenseTrackerTheme {
        CreateManualExpenseScreen(
            rememberNavController(),
            viewModel = CreateManualExpenseScreenViewModel()
        )
    }
}