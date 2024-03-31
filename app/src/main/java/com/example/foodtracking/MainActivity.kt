package com.example.foodtracking

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodtracking.ui.theme.FoodTrackingTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodTrackingTheme {
                val items = listOf(
                    BottomNavigationItem(
                        title = "Recipes",
                        selectedIcon = R.drawable.recipes_filled,
                        unselectedIcon = R.drawable.recipes_outlined,
                        route = "recipes"
                    ),
                    BottomNavigationItem(
                        title = "Shopping List",
                        selectedIcon = R.drawable.list_filled,
                        unselectedIcon = R.drawable.list_outlined,
                        route = "shopping_list"
                    ),
                    BottomNavigationItem(
                        title = "Calendar",
                        selectedIcon = R.drawable.calendar_filled,
                        unselectedIcon = R.drawable.calendar_outlined,
                        route = "calendar"
                    )
                )
                var selectedItem by remember { mutableStateOf(items.first()) }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = items,
                                selectedItem = selectedItem,
                                onItemSelected = { selectedItem = it }
                            )
                        }
                    ) {
                        // Content
                    }
                }
            }
        }
    }
}