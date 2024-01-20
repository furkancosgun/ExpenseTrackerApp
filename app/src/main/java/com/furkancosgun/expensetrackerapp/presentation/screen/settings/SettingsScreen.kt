package com.furkancosgun.expensetrackerapp.presentation.screen.settings

import android.os.UserManager
import android.provider.Settings
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.common.AppTextButton
import com.furkancosgun.expensetrackerapp.presentation.ui.common.UIPadding
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
import com.furkancosgun.expensetrackerapp.presentation.viewmodel.SettingsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsScreenViewModel = koinViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(UIPadding.MEDIUM.size)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.Unspecified),
            border = BorderStroke(1.dp,Color.Black)
        ) {
            AppTextButton(text = "Log Out") {
                viewModel.onLogOut()
                navController.navigate(Screen.Auth.route){
                    popUpTo(navController.graph.id){
                        inclusive = true
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun  SettingsScreen_Preview() {
    ExpenseTrackerTheme {
        SettingsScreen(navController = rememberNavController())
    }
}
