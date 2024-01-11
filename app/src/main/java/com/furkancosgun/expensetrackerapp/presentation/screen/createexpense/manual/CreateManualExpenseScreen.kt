package com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.manual

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateManualExpenseScreen(navController: NavController) {
    Scaffold {
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

            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Merchant Name",
                text = "",
                onTextChanged = {},
                icon = Icons.Default.PushPin
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Amount",
                text = "",
                onTextChanged = {},
                icon = Icons.Default.AttachMoney,
                keyboardType = KeyboardType.Decimal
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "12/12/2012",
                text = "",
                onTextChanged = {},
                icon = Icons.Default.DateRange,
                keyboardType = KeyboardType.Number
            )
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Description",
                text = "",
                onTextChanged = {},
                icon = Icons.Default.Description
            )
            AppOutlinedMenuField(
                modifier = Modifier.fillMaxWidth(),
                label = "Category",
            )
            AppTextButton(modifier = Modifier.fillMaxWidth(),text = "Create Category") {
                
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(UISpacing.MEDIUM.size)
            ) {
                Switch(checked = false, onCheckedChange = {})
                Text(text = "Include VAT Tax")
            }
            if (true) {
                AppOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = "VAT Tax",
                    text = "",
                    onTextChanged = {},
                    icon = Icons.Default.AttachMoney,
                    keyboardType = KeyboardType.Decimal
                )
            }
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Upload Image"
            ) {

            }
            if (true){
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    imageVector = Icons.Default.Photo,
                    contentDescription = "PHOTO",
                    contentScale = ContentScale.FillWidth
                )
            }
            Spacer(modifier = Modifier.height(UISpacing.MEDIUM.size))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Save Expense"
            ) {

            }
        }
    }
}

@Preview
@Composable
fun CreateManualExpenseScreen_Preview() {
    ExpenseTrackerTheme {
        CreateManualExpenseScreen(rememberNavController())
    }
}