package com.example.foodtracking.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodtracking.Screens.CalendarScreen
import com.example.foodtracking.Screens.RecipesScreen
import com.example.foodtracking.Screens.ShoppingListScreen


//Nowy brunch
@Composable
fun Navigation(
    //DinnersViewModel: DinnersViewModel, ShoppingListViewModel: ShoppingListViewModel, CalendarViewModel: CalendarViewModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ShoppingListScreen.route){
        composable(route = Screen.RecipesScreen.route){
            RecipesScreen(
                //DinnersViewModel,
                navController)
        }
        composable(route = Screen.ShoppingListScreen.route){
            ShoppingListScreen(
                //ShoppingListViewModel,
                navController)
        }
        composable(route = Screen.CalendarScreen.route){
            CalendarScreen(
                //CalendarViewModel,
                navController)
        }
    }
}