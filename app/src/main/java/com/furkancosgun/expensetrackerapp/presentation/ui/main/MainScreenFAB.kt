package com.furkancosgun.expensetrackerapp.presentation.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen

@Composable
fun MainScreenFAB(
    modifier: Modifier = Modifier,
    navController: NavController,
    isFabOpen: Boolean,
    onClick: () -> Unit
) {
    Row(modifier) {
        AnimatedVisibility(
            visible = isFabOpen,
            enter = expandHorizontally(expandFrom = Alignment.End),
            exit = shrinkHorizontally(shrinkTowards = Alignment.End)
        ) {
            Row {
                MainScreenFABItem(
                    icon = Icons.Default.FolderOpen,
                    contentDescription = stringResource(R.string.report)
                ) {
                    navController.navigate(Screen.App.CreateReport.route)
                }
                MainScreenFABItem(
                    icon = Icons.Default.FileOpen,
                    contentDescription = stringResource(R.string.expense),
                ) {
                    navController.navigate(Screen.App.CreateExpense.route)
                }
            }
        }
        MainScreenFABItem(
            icon = Icons.Default.Add,
            onClick = onClick
        )
    }
}