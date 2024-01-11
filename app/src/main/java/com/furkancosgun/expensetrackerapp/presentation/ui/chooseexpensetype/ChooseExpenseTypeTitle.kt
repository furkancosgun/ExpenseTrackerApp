package com.furkancosgun.expensetrackerapp.presentation.ui.chooseexpensetype

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor

@Composable
fun ChooseExpenseTypeTitle(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.choose_expense_type),
            style = MaterialTheme.typography.titleLarge,
            color = PrimaryColor
        )
        Text(text = stringResource(R.string.how_do_you_want_to_input_your_expense))
    }
}