package com.example.foodtracking.Screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodtracking.ui.theme.FoodTrackingTheme

@Composable
fun ShoppingListScreen(
    NavController: NavController
    //, ShoppingListViewModel: ShoppingListViewModel
){
    FoodTrackingTheme {
        Surface(
            modifier = Modifier.fillMaxSize().padding(15.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Text(
                "Shopping List Screen",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 20.dp),
                color = Color.Black
            )
        }
    }
}