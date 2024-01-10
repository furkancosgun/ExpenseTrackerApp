package com.furkancosgun.expensetrackerapp.presentation.screen.bottomnavigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.navigation.BottomNavScreen
import com.furkancosgun.expensetrackerapp.presentation.navigation.BottomNavigation
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.bottomnavigation.AppFloatingActionButton
import com.furkancosgun.expensetrackerapp.presentation.ui.bottomnavigation.BottomNavigationNavBarItem
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.WhiteColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationScreen(navController: NavController) {

    val navBarController = rememberNavController()
    val bottomScreenList = mutableListOf(
        BottomNavScreen.Home,
        BottomNavScreen.Settings,
    )
    val navBackStackEntry by navBarController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var isFabOpen by remember {
        mutableStateOf(false)
    }


    Scaffold(
        floatingActionButton = {
            Row {
                AnimatedVisibility(
                    visible = isFabOpen,
                    enter = expandHorizontally(expandFrom = Alignment.End),
                    exit = shrinkHorizontally(shrinkTowards = Alignment.End)
                ) {
                    Row {
                        AppFloatingActionButton(
                            icon = Icons.Default.FolderOpen,
                            contentDescription = stringResource(R.string.report)
                        ) {
                            navController.navigate(Screen.App.CreateReport.route)
                        }
                        AppFloatingActionButton(
                            icon = Icons.Default.FileOpen,
                            contentDescription = stringResource(R.string.expense),
                        ) {
                            navController.navigate(Screen.App.CreateExpense.route)
                        }
                    }
                }
                AppFloatingActionButton(
                    icon = Icons.Default.Add,
                ) {
                    isFabOpen = !isFabOpen
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = WhiteColor
            ) {
                bottomScreenList.forEach { screen ->
                    BottomNavigationNavBarItem(
                        navController = navBarController,
                        selected = currentRoute == screen.route,
                        screen = screen
                    )
                }
            }

        }
    )
    {
        BottomNavigation(navController = navBarController)
    }
}

@Preview
@Composable
fun BottomNavigationScreen_Preview() {
    ExpenseTrackerTheme {
        BottomNavigationScreen(navController = rememberNavController())
    }
}