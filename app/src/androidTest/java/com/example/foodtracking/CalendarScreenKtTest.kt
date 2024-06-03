package com.example.foodtracking

import ConfirmDishChangeDialog
import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foodtracking.Databases.Calendar.CalendarViewModel
import com.example.foodtracking.Databases.Calendar.CalendarViewModelFactory
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import com.example.foodtracking.Databases.ShoppingList.ListViewModelFactory
import com.example.foodtracking.Screens.CalendarScreen
import com.example.foodtracking.ui.theme.FoodTrackingTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalendarScreenKtTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            FoodTrackingTheme {
                val listViewModel: ListViewModel = viewModel(
                    factory = ListViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                val calendarViewModel: CalendarViewModel = viewModel(
                    factory = CalendarViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                calendarViewModel.addCalendarItem("04/06/2024", 1)
                CalendarScreen(
                    navController = null,
                    calendarViewModel = calendarViewModel,
                    listViewModel = listViewModel
                )
            }
        }
    }

    @Test
    fun verifyIfAllViewsExists(){
        composeTestRule.onNodeWithTag("calendarCard").assertIsDisplayed()
        composeTestRule.onNodeWithTag("datePicker").assertIsDisplayed()
        composeTestRule.onNodeWithTag("noDishSelected").assertIsDisplayed()
    }

    @Test
    fun verifyIfTextIsCorrect(){
        composeTestRule.onNodeWithTag("noDishSelected").assertTextEquals("You haven't yet selected a dish for ")
    }

    @Test
    fun verifyIfDatePickerIsClickable(){
        composeTestRule.onNodeWithTag("datePicker").performClick()

}