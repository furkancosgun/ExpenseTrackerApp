package com.furkancosgun.expensetrackerapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(val route:String,val icon:ImageVector){
    data object Home : BottomNavScreen("home", icon = Icons.Default.Home)
}
