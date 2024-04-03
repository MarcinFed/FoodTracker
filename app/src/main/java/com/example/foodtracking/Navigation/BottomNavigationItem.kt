package com.example.foodtracking.Navigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
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

@Composable
fun BottomNavItem(
    item: BottomNavigationItem,
    isSelected: Boolean
) {
    val animatedHeight by animateDpAsState(targetValue = if (isSelected) 56.dp else 48.dp)
    val animatedElevation by animateDpAsState(targetValue = if (isSelected) 10.dp else 0.dp)
    val animatedAlpha by animateFloatAsState(targetValue = if (isSelected) 1f else 0.5f)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(animatedHeight)
            .alpha(animatedAlpha),
        contentAlignment = Alignment.Center
    ) {
        FlipIcon(
            isSelected = isSelected,
            activeIcon = item.selectedIcon,
            inactiveIcon = item.unselectedIcon,
            modifier = Modifier.size(24.dp)
        )
    }
}
