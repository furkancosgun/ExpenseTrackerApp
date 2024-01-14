package com.furkancosgun.expensetrackerapp.presentation.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.data.repository.UserSessionManagerRepository
import com.furkancosgun.expensetrackerapp.presentation.navigation.Screen
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.PrimaryColor
import kotlinx.coroutines.delay
import org.koin.compose.koinInject


@Composable
fun SplashScreen(
    navController: NavController,
    userSessionManagerRepository: UserSessionManagerRepository = koinInject()
) {
    LaunchedEffect(key1 = true) {
        userSessionManagerRepository.logoutUser()
        delay(3000L)
        if (userSessionManagerRepository.isLoggedIn()) {
            userSessionManagerRepository.getUser()
            navController.navigate(Screen.App.route)
        } else {
            navController.navigate(Screen.Auth.route)
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayLarge,
            color = PrimaryColor
        )
    }
}
