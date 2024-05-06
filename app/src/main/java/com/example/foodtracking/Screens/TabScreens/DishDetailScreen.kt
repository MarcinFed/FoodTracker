package com.example.foodtracking.Screens.TabScreens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.foodtracking.Databases.Food.Dish
import com.example.foodtracking.Databases.Food.DishRepository


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishDetailScreen(dishId: Int?, navController: NavController) {
    val dish = DishRepository.getDish(dishId)

    // Pamiętaj, aby obsłużyć sytuację, gdy danie nie zostanie znalezione
    if (dish == null) {
        Text("Dish not found")
        return
    }

    val scale = remember { Animatable(1f) }
    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 1.2f, animationSpec = tween(700))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(dish.name) },
                navigationIcon = {
                    TextButton(onClick = { navController.navigateUp() }) {
                        Text("Back", color = Color.White)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Image(
                painter = rememberImagePainter(dish.imageRes),
                contentDescription = "${dish.name} full image",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .graphicsLayer {
                        scaleX = scale.value
                        scaleY = scale.value
                    }
            )
            Text(dish.description)
            // Reszta szczegółów przepisu
        }
    }
}