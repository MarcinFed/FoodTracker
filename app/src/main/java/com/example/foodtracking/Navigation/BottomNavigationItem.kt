package com.example.foodtracking.Navigation

import androidx.annotation.DrawableRes
import com.example.foodtracking.R

data class BottomNavigationItem(
    val title: String = "",
    @DrawableRes val selectedIcon: Int= 0,
    @DrawableRes val unselectedIcon: Int = 0,
    val route: String = "",
    val badgeCount: Int? = null
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                title = "Recipes",
                selectedIcon = R.drawable.recipes_filled,
                unselectedIcon = R.drawable.recipes_outlined,
                route = Screen.RecipesScreen.route
            ),
            BottomNavigationItem(
                title = "Shopping List",
                selectedIcon = R.drawable.list_filled,
                unselectedIcon = R.drawable.list_outlined,
                route = Screen.ShoppingListScreen.route
            ),
            BottomNavigationItem(
                title = "Calendar",
                selectedIcon = R.drawable.calendar_filled,
                unselectedIcon = R.drawable.calendar_outlined,
                route = Screen.CalendarScreen.route
            )
        )
    }
}