package com.furkancosgun.expensetrackerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.furkancosgun.expensetrackerapp.presentation.navigation.navhost.ApplicationNavigation
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ApplicationNavigation()
                }
            }
        }
    }
}
//https://www.figma.com/file/Qws5eq3k7ZpYwSdXy5Ks8k/ExpenseTracker-(Full-Mobile-App)-(Community)?type=design&node-id=901-3173&mode=design&t=sr9N72nbKetDuZYR-0