package com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.manual

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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.R
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
    Scaffold { pad ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(pad)
                .padding(UIPadding.MEDIUM.size)
        ) {
            Text(
                text = stringResource(R.string.create_expense_with_manual),
                style = MaterialTheme.typography.titleLarge,
                color = PrimaryColor
            )
            Text(text = stringResource(R.string.you_can_record_your_expenses_in_detail))

            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Merchant Name",
                text = viewModel.state.merchantName,
                onTextChanged = {
                    viewModel.onEvent(CreateManualExpenseEvent.MerchantNameChanged(it))
                },
                icon = Icons.Default.PushPin,
                errorText = viewModel.state.merchantNameError
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Amount",
                text = viewModel.state.amount.toString(),
                onTextChanged = {
                    viewModel.onEvent(CreateManualExpenseEvent.AmountChanged(it.toDouble()))
                },
                icon = Icons.Default.AttachMoney,
                keyboardType = KeyboardType.Decimal,
                errorText = viewModel.state.amountError
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "12/12/2012",
                text = viewModel.state.date,
                onTextChanged = {
                    viewModel.onEvent(CreateManualExpenseEvent.DateChanged(it))
                },
                icon = Icons.Default.DateRange,
                keyboardType = KeyboardType.Number,
                errorText = viewModel.state.dateError
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Description",
                text = viewModel.state.description,
                onTextChanged = {
                    viewModel.onEvent(CreateManualExpenseEvent.DescriptionChanged(it))
                },
                icon = Icons.Default.Description
            )
            AppOutlinedMenuField(
                modifier = Modifier.fillMaxWidth(),
                label = "Category",
            )
            AppTextButton(modifier = Modifier.fillMaxWidth(), text = "Create Category") {
                viewModel.onEvent(CreateManualExpenseEvent.CreateCategory)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(UISpacing.MEDIUM.size)
            ) {
                Switch(checked = viewModel.state.includeVat, onCheckedChange = {
                    viewModel.onEvent(CreateManualExpenseEvent.IncludeVatChanged(it))
                })
                Text(text = "Include VAT Tax")
            }
            AnimatedVisibility(visible = viewModel.state.includeVat) {
                AppOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = "VAT Tax",
                    text = viewModel.state.vat.toString(),
                    onTextChanged = {
                        viewModel.onEvent(CreateManualExpenseEvent.VatChanged(it.toDouble()))
                    },
                    icon = Icons.Default.AttachMoney,
                    keyboardType = KeyboardType.Decimal
                )
            }

            AnimatedVisibility(visible = viewModel.state.uploadedImage!=null) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    bitmap = viewModel.state.uploadedImage?: ImageBitmap(1,1),
                    contentDescription = "PHOTO",
                    contentScale = ContentScale.FillWidth
                )
            }
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Upload Image"
            ) {
                viewModel.onEvent(CreateManualExpenseEvent.UploadImage)
            }

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Save Expense"
            ) {
                viewModel.onEvent(CreateManualExpenseEvent.Submit)
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