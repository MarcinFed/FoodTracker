package com.example.foodtracking.Navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.compose.rememberNavController


@Composable
fun BottomNavigationBar() {
    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = index == navigationSelectedItem,
                        label = {
                            Text(navigationItem.title)
                        },
                        icon = {
                            Icon(
                            painterResource(if (index == navigationSelectedItem) navigationItem.selectedIcon else navigationItem.unselectedIcon),
                                contentDescription = navigationItem.title,)
                        },
                        onClick = {
                            navigationSelectedItem = index
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
    ) {
        paddingValues ->
    }
}


//@Composable
//fun BottomNavigationBar(
//    items: List<BottomNavigationItem>,
//    selectedItem: BottomNavigationItem,
//    onItemSelected: (BottomNavigationItem) -> Unit
//) {
//    Box(modifier = Modifier.border(1.dp, Color.LightGray)) {
//        NavigationBar {
//            items.forEach { item ->
//                NavigationBarItem(
//                    selected = selectedItem == item,
//                    onClick = { onItemSelected(item) },
//                    label = { Text(text = item.title) },
//                    icon = {
//                        BadgedBox(
//                            badge = {
//                                item.badgeCount?.let {
//                                    Badge { Text(text = it.toString()) }
//                                }
//                            }
//                        ) {
//                            Icon(
//                                painter = painterResource(if (item == selectedItem) item.selectedIcon else item.unselectedIcon),
//                                contentDescription = item.title,
//                                modifier = Modifier.size(24.dp)
//                            )
//                        }
//                    }
//                )
//            }
//        }
//    }
//}
