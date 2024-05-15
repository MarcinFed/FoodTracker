package com.example.foodtracking

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodtracking.Databases.Calendar.CalendarViewModel
import com.example.foodtracking.Databases.Calendar.CalendarViewModelFactory
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import com.example.foodtracking.Databases.ShoppingList.ListViewModelFactory
import com.example.foodtracking.Navigation.BottomNavigationBar
import com.example.foodtracking.ui.theme.FoodTrackingTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodTrackingTheme {
                val listViewModel: ListViewModel = viewModel(
                    factory = ListViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                val calendarViewModel: CalendarViewModel = viewModel(
                    factory = CalendarViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(listViewModel, calendarViewModel)
                        }
                    ){

                    }
                }
            }
        }
    }
}