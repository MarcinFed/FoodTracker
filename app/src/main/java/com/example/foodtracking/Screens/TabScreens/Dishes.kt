package com.example.foodtracking.Screens.TabScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodtracking.ui.theme.FoodTrackingTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Dishes() {
    Scaffold {
        FoodTrackingTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 15.dp, start = 15.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                Text("Ola")
            }
        }
    }
}