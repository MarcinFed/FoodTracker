package com.example.foodtracking.Navigation

sealed class Screen(val route: String){
    object RecipesScreen : Screen("recipes_screen")
    object ShoppingListScreen : Screen("shopping_list_screen")
    object CalendarScreen : Screen("calendar_screen")
}