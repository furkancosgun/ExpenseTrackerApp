package com.furkancosgun.expensetrackerapp.presentation.screen.bottomnavigation

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.furkancosgun.expensetrackerapp.presentation.navigation.BottomNavScreen
import com.furkancosgun.expensetrackerapp.presentation.navigation.BottomNavigation
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationScreen(navController: NavHostController) {
    val bottomScreenList = mutableListOf(
        BottomNavScreen.Home,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomAppBar {
                bottomScreenList.forEach { screen ->
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryColor,
                            selectedTextColor = PrimaryColor,
                            indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                LocalAbsoluteTonalElevation.current
                            )
                        ),
                        selected = currentRoute == screen.route,
                        alwaysShowLabel = true,
                        label = {
                            Text(text = screen.route)
                        },
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.route
                            )
                        },
                    )
                }
            }

        }
    )
    {
        BottomNavigation(navController = navController)
    }
}