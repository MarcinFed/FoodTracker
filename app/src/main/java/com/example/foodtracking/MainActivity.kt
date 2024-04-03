package com.example.foodtracking

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.foodtracking.Navigation.BottomNavigationBar
import com.example.foodtracking.Navigation.BottomNavigationItem
import com.example.foodtracking.ui.theme.FoodTrackingTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodTrackingTheme {
                var selectedItem by remember { mutableStateOf(items.first()) }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = items,
                                selectedItem = selectedItem,
                                onItemSelected = { selectedItem = it }
                            )
                        }
                    ) {
                        // Content
                    }
                }
            }
        }
    }
}