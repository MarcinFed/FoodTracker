package com.example.foodtracking.Screens.TabScreens

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.foodtracking.Databases.Food.Category
import com.example.foodtracking.Databases.Food.Dish
import com.example.foodtracking.Databases.Food.Ingredient
import com.example.foodtracking.R
import com.example.foodtracking.ui.theme.FoodTrackingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.example.foodtracking.Databases.Calendar.CalendarViewModel
import com.example.foodtracking.Databases.Food.DishRepository
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import com.example.foodtracking.Navigation.Screen
import com.example.foodtracking.Screens.PopUp.PopUp

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Dishes(pagerState: PagerState, coroutineScope: CoroutineScope, navController: NavController, listViewModel: ListViewModel, calendarViewModel: CalendarViewModel) {
    val sharedPreferences = LocalContext.current.getSharedPreferences("FoodTracking", 0)
    val category = sharedPreferences.getString("category", "All") ?: "All"
    val dishes = remember {DishRepository.getDishes(category)}
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString("category", "All").apply()

    Scaffold {
        FoodTrackingTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 15.dp, start = 15.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                RecipeList(dishes, coroutineScope = coroutineScope, pagerState = pagerState, navController = navController, listViewModel = listViewModel, calendarViewModel = calendarViewModel)
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeList(dishes: List<Dish>, coroutineScope: CoroutineScope, pagerState: PagerState, navController: NavController, listViewModel: ListViewModel, calendarViewModel: CalendarViewModel) {
    LazyColumn {
        items(dishes) { dish ->
            RecipeListItem(dish = dish, coroutineScope = coroutineScope, pagerState = pagerState, navController = navController, listViewModel = listViewModel, calendarViewModel = calendarViewModel)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeListItem(dish: Dish, coroutineScope: CoroutineScope, pagerState: PagerState, navController: NavController, listViewModel: ListViewModel, calendarViewModel: CalendarViewModel) {
    val context = LocalContext.current
    var rotationAngle = remember { Animatable(0f) }
    val showPopup = remember { mutableStateOf(false) } // State to control popup visibility

    if (showPopup.value) {
        PopUp(onDismiss = { showPopup.value = false }, listViewModel = listViewModel, dish = dish, calendarViewModel = calendarViewModel) // Pass a dismiss handler to close the popup
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),
        onClick = {
            coroutineScope.launch {
                navController.navigate(Screen.DishDetailScreen.route + "/${dish.id}")
            }
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = dish.imageRes,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = "${dish.name} image",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .height(100.dp)
                    .width(100.dp),
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier
                    .padding(end = 8.dp, start = 8.dp)
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text(
                    text = dish.name,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = dish.description,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    color = Color.Gray
                )
            }
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        rotationAngle.animateTo(
                            targetValue = 360f,
                            animationSpec = TweenSpec(durationMillis = 600)
                        )
                        rotationAngle.snapTo(0f)
                    }
                    showPopup.value = true
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(50.dp)
                    .graphicsLayer {
                        rotationZ = rotationAngle.value
                    }

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Add",
                    tint = Color.Black,
                    modifier = Modifier.padding(8.dp),
                )
            }
        }
    }
}