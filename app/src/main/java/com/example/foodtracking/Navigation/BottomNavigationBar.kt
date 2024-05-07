package com.example.foodtracking.Navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import com.example.foodtracking.Screens.CalendarScreen
import com.example.foodtracking.Screens.RecipesScreen
import com.example.foodtracking.Screens.ShoppingListScreen
import com.example.foodtracking.Screens.TabScreens.DishDetailScreen


@Composable
fun BottomNavigationBar(
    listViewModel: ListViewModel,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(bottom = 10.dp, start = 20.dp, end = 20.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
            ) {
            // Drawing a line above the navigation bar
                NavigationBar(
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
    //                    .drawBehind {
    //                        val strokeWidth = 1.dp.toPx() // Thickness of the line
    //                        drawLine(
    //                            Color.Gray, // Color of the line
    //                            start = Offset(x = 0f, y = 0f), // Start point of the line (top left)
    //                            end = Offset(x = size.width, y = 0f), // End point of the line (top right)
    //                            strokeWidth = strokeWidth
    //                        )
    //                    }
                ) {
                    BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->
                        val isSelected = navigationItem.route == currentDestination?.route
                        NavigationBarItem(
                            icon = { BottomNavItem(navigationItem, isSelected) },
                            label = { Text(text = navigationItem.title) },
                            selected = isSelected,
                            onClick = {
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.RecipesScreen.route,
            modifier = Modifier.padding(paddingValues = paddingValues)) {
            composable(Screen.RecipesScreen.route) {
                RecipesScreen(navController = navController)
            }
            composable(Screen.ShoppingListScreen.route) {
                ShoppingListScreen(listViewModel)
            }
            composable(Screen.CalendarScreen.route) {
                CalendarScreen(navController = navController)
            }
            composable(route = Screen.DishDetailScreen.route + "/{id}"){
                val dish = it.arguments?.getInt("id")
                Log.println(Log.ERROR, "Dish id", dish.toString())
                DishDetailScreen(dishId = dish, navController = navController)
            }
        }
    }
}