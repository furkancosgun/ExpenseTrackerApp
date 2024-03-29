package com.furkancosgun.expensetrackerapp.presentation.screen.createcategory

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppOutlinedTextField
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppTextButton
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.CreateCategoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateCategoryAlert(
    anyButtonClicked: () -> Unit,
    viewModel: CreateCategoryViewModel = koinViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.event.collect {
            when (it) {
                is CreateCategoryViewModel.CreateCategoryViewModelEvent.Error -> {
                    Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                }

                is CreateCategoryViewModel.CreateCategoryViewModelEvent.Success -> {
                    Toast.makeText(context, "Category Saved",Toast.LENGTH_SHORT).show()
                    anyButtonClicked.invoke()
                }
            }
        }
    }

    AlertDialog(
        onDismissRequest = anyButtonClicked,
        title = { Text(text = stringResource(R.string.add_new_category)) },
        text = {
            AppOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.enter_category_name),
                text = viewModel.state.category,
                onTextChanged = { viewModel.onEvent(CreateCategoryAlertEvent.CategoryChanged(it)) },
                icon = Icons.Default.Category,
                errorText = viewModel.state.categoryError
            )
        },
        dismissButton = {
            AppTextButton(text = stringResource(R.string.close), onClick = anyButtonClicked)
        },
        confirmButton = {
            AppButton(text = stringResource(R.string.save), onClick = {
                viewModel.onEvent(CreateCategoryAlertEvent.Submit)
            })
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun CreateCategoryScreen_Preview() {
    ExpenseTrackerTheme {
    }
}