package com.furkancosgun.expensetrackerapp.presentation.screen.chooseexpensetype

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.ui.chooseexpensetype.ChooseExpenseTypeItem
import com.furkancosgun.expensetrackerapp.presentation.ui.chooseexpensetype.ChooseExpenseTypeTitle
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UISpacing
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.ChooseExpenseScreenViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ChooseExpenseTypeScreen(
    navController: NavController,
    viewModel: ChooseExpenseScreenViewModel = koinViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UIPadding.MEDIUM.size)
    ) {
        ChooseExpenseTypeTitle()

        Spacer(modifier = Modifier.height(UISpacing.LARGE.size))

        Column {
            viewModel.selectableExpenseTypes.forEach { expenseType ->
                ChooseExpenseTypeItem(
                    title = stringResource(id = expenseType.title),
                    isSelected = expenseType == viewModel.state.expenseType
                ) {
                    viewModel.onEvent(
                        ChooseExpenseTypeScreenEvent.ExpenseTypeChanged(
                            expenseType
                        )
                    )
                }
            }
        }
        AppButton(
            modifier = Modifier.fillMaxWidth(), text = stringResource(R.string.continue_it),
        ) {
            navController.navigate(viewModel.state.expenseType.screen.route)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ChooseExpenseScreen_Preview() {
    ExpenseTrackerTheme {
        ChooseExpenseTypeScreen(
            navController = rememberNavController(),
            viewModel = ChooseExpenseScreenViewModel()
        )
    }
}