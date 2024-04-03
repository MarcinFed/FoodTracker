package com.example.foodtracking.Navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodtracking.Screens.CalendarScreen
import com.example.foodtracking.Screens.RecipesScreen
import com.example.foodtracking.Screens.ShoppingListScreen


@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Box(
                modifier = Modifier
                    .border(width = 1.dp, color = Color.Gray)
            ) {
                NavigationBar {
                    BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->
                        NavigationBarItem(
                            selected = navigationItem.route == currentDestination?.route,
                            label = {
                                Text(navigationItem.title)
                            },
                            icon = {
                                Icon(
                                    painterResource(if (navigationItem.route == currentDestination?.route) navigationItem.selectedIcon else navigationItem.unselectedIcon),
                                    contentDescription = navigationItem.title,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
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
    ) {
        paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.RecipesScreen.route,
            modifier = Modifier.padding(paddingValues = paddingValues)) {
            composable(Screen.RecipesScreen.route) {
                RecipesScreen(NavController = navController)
            }
            composable(Screen.ShoppingListScreen.route) {
                ShoppingListScreen(NavController = navController)
            }
            composable(Screen.CalendarScreen.route) {
                CalendarScreen(NavController = navController)
            }
        }

    }

}
