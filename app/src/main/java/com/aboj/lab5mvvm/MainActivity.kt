package com.aboj.lab5mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.aboj.lab5mvvm.ui.theme.Lab5MVVMTheme
import com.aboj.lab5mvvm.ViewModel.GeneralViewModel
import com.aboj.lab5mvvm.Views.TodoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5MVVMTheme {
                val navController = rememberNavController()
                val viewModel: GeneralViewModel = viewModel()

                NavHost(navController = navController, startDestination = "todo") {
                    composable("todo") {
                        TodoScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
