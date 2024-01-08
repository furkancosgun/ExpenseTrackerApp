package com.furkancosgun.expensetrackerapp.presentation.screen.splash

import android.window.SplashScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController:NavController) {
    LaunchedEffect(key1 = true){
        delay(3000L)
        navController.navigate(Screen.Auth.route)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.displayLarge, color = PrimaryColor)
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashScreen_Preview() {
    ExpenseTrackerTheme {
        SplashScreen(rememberNavController())
    }
}