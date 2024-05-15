package com.example.foodtracking.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.foodtracking.Databases.Calendar.CalendarViewModel
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import com.example.foodtracking.R
import com.example.foodtracking.Screens.TabScreens.Categories
import com.example.foodtracking.Screens.TabScreens.Dishes
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipesScreen(
    navController: NavController,
    listViewModel: ListViewModel,
    calendarViewModel: CalendarViewModel
) {
    Column() {
        val pagerState = rememberPagerState(pageCount = {2})
        val coroutineScope = rememberCoroutineScope()
        TabRow(selectedTabIndex = pagerState.currentPage,
            contentColor = Color.Gray,
            modifier = Modifier.fillMaxWidth(0.66f),
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    height = 2.dp,
                    color = colorResource(id = R.color.blue)
                )
            }
        ) {
            Tab(
                selected = pagerState.currentPage == 0,
                text = {
                       Text(text = "Categories",
                           color = if (pagerState.currentPage == 0) colorResource(id = R.color.blue) else Color.Gray,
                           fontWeight = if(pagerState.currentPage ==0) androidx.compose.ui.text.font.FontWeight.W900 else androidx.compose.ui.text.font.FontWeight.Normal)
                },
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }
            )
            Tab(
                selected = pagerState.currentPage == 1,
                text = {
                    Text(text = "Dishes",
                        color = if (pagerState.currentPage == 1) colorResource(id = R.color.blue) else Color.Gray,
                        fontWeight = if(pagerState.currentPage == 1) androidx.compose.ui.text.font.FontWeight.W900 else androidx.compose.ui.text.font.FontWeight.Normal)
                },
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                }
            )
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true
        ) { page ->
            when (page) {
                0 -> Categories(pagerState = pagerState, coroutineScope = coroutineScope)
                1 -> Dishes(pagerState = pagerState, coroutineScope = coroutineScope, navController = navController, listViewModel = listViewModel, calendarViewModel = calendarViewModel)
            }
        }
    }
}