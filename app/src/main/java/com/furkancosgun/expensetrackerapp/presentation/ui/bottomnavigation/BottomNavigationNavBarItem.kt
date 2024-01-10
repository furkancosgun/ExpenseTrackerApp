package com.furkancosgun.expensetrackerapp.presentation.ui.bottomnavigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.furkancosgun.expensetrackerapp.presentation.navigation.BottomNavScreen
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor

@Composable
fun RowScope.BottomNavigationNavBarItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    selected: Boolean,
    screen: BottomNavScreen
) {
    NavigationBarItem(
        modifier = modifier,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = PrimaryColor,
            selectedTextColor = PrimaryColor,
            indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                LocalAbsoluteTonalElevation.current
            )
        ),
        selected = selected,
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