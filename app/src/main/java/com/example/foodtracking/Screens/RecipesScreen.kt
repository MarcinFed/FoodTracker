package com.example.foodtracking.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodtracking.Navigation.BottomNavigationBar
import com.example.foodtracking.ui.theme.FoodTrackingTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecipesScreen(
    NavController: NavController
    //, DinnersViewModel: DinnersViewModel
) {
    Scaffold {
        FoodTrackingTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                Text(
                    "Recipes Screen",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 20.dp),
                    color = Color.Black
                )
            }
        }
    }

}