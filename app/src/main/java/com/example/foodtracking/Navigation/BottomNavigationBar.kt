package com.example.foodtracking.Navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


class BottomNavigationItem(
    val title: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val route: String,
    val badgeCount: Int? = null
)


@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItem: BottomNavigationItem,
    onItemSelected: (BottomNavigationItem) -> Unit
) {
    Box(modifier = Modifier.border(1.dp, Color.LightGray)) {
        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    selected = selectedItem == item,
                    onClick = { onItemSelected(item) },
                    label = { Text(text = item.title) },
                    icon = {
                        BadgedBox(
                            badge = {
                                item.badgeCount?.let {
                                    Badge { Text(text = it.toString()) }
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(if (item == selectedItem) item.selectedIcon else item.unselectedIcon),
                                contentDescription = item.title,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                )
            }
        }
    }
}
