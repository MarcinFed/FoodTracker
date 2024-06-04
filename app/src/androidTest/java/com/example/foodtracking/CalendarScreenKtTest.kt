package com.example.foodtracking

import ConfirmDishChangeDialog
import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foodtracking.Databases.Calendar.CalendarRepository
import com.example.foodtracking.Databases.Calendar.CalendarViewModel
import com.example.foodtracking.Databases.Calendar.CalendarViewModelFactory
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import com.example.foodtracking.Databases.ShoppingList.ListViewModelFactory
import com.example.foodtracking.Screens.CalendarScreen
import com.example.foodtracking.ui.theme.FoodTrackingTheme
import io.mockk.MockKSettings.relaxed
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.Date

@RunWith(AndroidJUnit4::class)
class CalendarScreenKtTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    private lateinit var calendarViewModel: CalendarViewModel
    private lateinit var listViewModel: ListViewModel

    @Before
    fun setUp() {
        composeTestRule.setContent {
            Dispatchers.setMain(Dispatchers.Unconfined)
            FoodTrackingTheme {
                listViewModel = viewModel(
                    factory = ListViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                calendarViewModel = viewModel(
                    factory = CalendarViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                CalendarScreen(
                    navController = null,
                    calendarViewModel = calendarViewModel,
                    listViewModel = listViewModel
                )
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun verifyIfAllViewsExists() {
        composeTestRule.onNodeWithTag("calendarCard").assertExists()
        composeTestRule.onNodeWithTag("datePicker").assertExists()
        composeTestRule.onNodeWithTag("noDishSelected", useUnmergedTree = true).assertExists()
    }

    @Test
    fun verifyIfNoDishTextIsCorrect() {
        composeTestRule.onNodeWithTag("noDishSelected", useUnmergedTree = true)
            .assertTextContains(substring = true, value = "You haven't yet selected a dish for")
    }

    @Test
    fun verifyIfDatePickerIsClickable() {
        composeTestRule.onNodeWithTag("datePicker").performClick()
    }

    @Test
    fun verifyIfNoDishSelectedIsNotClickable() {
        composeTestRule.onNodeWithTag("noDishSelected", useUnmergedTree = true).assertHasNoClickAction()
    }

    @Test
    fun verifyIfAllViewsAreDisplayed() {
        composeTestRule.onNodeWithTag("calendarCard").assertIsDisplayed()
        composeTestRule.onNodeWithTag("datePicker").assertIsDisplayed()
        composeTestRule.onNodeWithTag("noDishSelected", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun verifyCalendarCardDimensionsAndAlignment() {
        composeTestRule.onNodeWithTag("calendarCard").apply {
            assertHeightIsEqualTo(400.dp)
            assertIsDisplayed()
        }
    }
}