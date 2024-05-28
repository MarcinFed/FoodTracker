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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodtracking.Databases.Calendar.CalendarViewModel
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import com.example.foodtracking.Screens.CalendarScreen
import com.example.foodtracking.Screens.RecipesScreen
import com.example.foodtracking.Screens.ShoppingListScreen
import com.example.foodtracking.Screens.TabScreens.DishDetailScreen


@Composable
fun BottomNavigationBar(
    listViewModel: ListViewModel,
    calendarViewModel: CalendarViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (currentDestination?.route != Screen.DishDetailScreen.route + "/{id}") {
                NavigationBarCard(navController, navBackStackEntry, currentDestination)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.RecipesScreen.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screen.RecipesScreen.route) {
                RecipesScreen(
                    navController = navController,
                    listViewModel = listViewModel,
                    calendarViewModel = calendarViewModel
                )
            }
            composable(Screen.ShoppingListScreen.route) {
                ShoppingListScreen(listViewModel)
            }
            composable(Screen.CalendarScreen.route) {
                CalendarScreen(
                    navController = navController,
                    calendarViewModel = calendarViewModel,
                    listViewModel = listViewModel
                )
            }
            composable(route = Screen.DishDetailScreen.route + "/{id}") { backStackEntry ->
                val dishId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                Log.d("DishDetailScreen", "Dish id: $dishId")
                DishDetailScreen(
                    dishId = dishId,
                    navController = navController,
                    listViewModel = listViewModel,
                    calendarViewModel = calendarViewModel
                )
            }
        }
    }
}

@Composable
fun NavigationBarCard(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry?,
    currentDestination: NavDestination?
) {
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
        ) {
            BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->
                val isSelected = navigationItem.route == currentDestination?.route
                NavigationBarItem(
                    modifier = Modifier.testTag(navigationItem.title),
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