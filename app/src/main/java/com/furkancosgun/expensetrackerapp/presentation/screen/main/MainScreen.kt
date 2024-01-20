package com.furkancosgun.expensetrackerapp.presentation.screen.main

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.presentation.navigation.BottomNavScreen
import com.furkancosgun.expensetrackerapp.presentation.navigation.navhost.BottomNavigation
import com.furkancosgun.expensetrackerapp.presentation.ui.main.MainScreenFAB
import com.furkancosgun.expensetrackerapp.presentation.ui.main.MainScreenNavBarItem
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.MainScreenViewModel
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, viewModel: MainScreenViewModel = koinViewModel()) {

    val navBarController = rememberNavController()
    val bottomScreenList = mutableListOf(
        BottomNavScreen.Home,
        BottomNavScreen.Settings,
    )
    val navBackStackEntry by navBarController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        floatingActionButton = {
            MainScreenFAB(
                navController = navController,
                isFabOpen = viewModel.state.isFabOpen,
            ) {
                viewModel.onEvent(MainScreenEvent.FabToggle)
            }
        },
        bottomBar = {
            BottomAppBar {
                bottomScreenList.forEach { screen ->
                    MainScreenNavBarItem(
                        navController = navBarController,
                        selected = currentRoute == screen.route,
                        screen = screen
                    )
                }
            }
        }
    )
    {
        BottomNavigation(navBarHostController = navBarController,navController=navController)
    }
}

@Preview
@Composable
fun MainScreen_Preview() {
    ExpenseTrackerTheme {
        MainScreen(
            navController = rememberNavController(),
            viewModel = MainScreenViewModel()
        )
    }
}