package com.example.foodtracking

import ConfirmDishChangeDialog
import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
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
class CalendarScreenWithDishKtTest {

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
                calendarViewModel.addCalendarItem(SimpleDateFormat("dd/MM/yyyy").format(Date()), 1)
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
        composeTestRule.onNodeWithTag("dishSelected", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("spacer1", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("spacer2", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("spacer3", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("dishImage", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("dishName", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("dishDescription", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("deleteDish", useUnmergedTree = true).assertExists()
    }

    @Test
    fun verifyIfDatePickerIsClickable() {
        composeTestRule.onNodeWithTag("datePicker").performClick()
    }

    @Test
    fun verifyIfAllViewsAreDisplayed() {
        composeTestRule.onNodeWithTag("calendarCard").assertIsDisplayed()
        composeTestRule.onNodeWithTag("datePicker").assertIsDisplayed()
        composeTestRule.onNodeWithTag("dishSelected", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("spacer1", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("spacer2", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("spacer3", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("dishImage", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("dishName", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("dishDescription", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("deleteDish", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun verifyIfDishSelectedTextIsCorrect() {
        composeTestRule.onNodeWithTag("dishSelected", useUnmergedTree = true)
            .assertTextContains(substring = true, value = "Dish for")
    }

    @Test
    fun verifyIfDeleteDishButtonIsClickable() {
        composeTestRule.onNodeWithTag("moreOptions", useUnmergedTree = true).assertHasClickAction()
    }

    @Test
    fun verifyIfDishNameIsCorrect() {
        composeTestRule.onNodeWithTag("dishName", useUnmergedTree = true)
            .assertTextEquals("Pepperoni Pizza")
    }

    @Test
    fun verifyIfDishDescriptionIsCorrect() {
        composeTestRule.onNodeWithTag("dishDescription", useUnmergedTree = true)
            .assertTextEquals("Spicy pepperoni pizza with a crispy crust and lots of cheese.")
    }

    @Test
    fun verifyDishCardLayoutAndSpacing() {
        composeTestRule.onNodeWithTag("dishCard").apply {
            assertIsDisplayed()
            onChildren().assertCountEquals(1)
        }

        composeTestRule.onNodeWithTag("spacer1", useUnmergedTree = true)
            .assertHeightIsEqualTo(16.dp)
        composeTestRule.onNodeWithTag("spacer2", useUnmergedTree = true).assertHeightIsEqualTo(8.dp)
        composeTestRule.onNodeWithTag("spacer3", useUnmergedTree = true)
            .assertHeightIsEqualTo(16.dp)

        composeTestRule.onNodeWithTag("dishImage", useUnmergedTree = true)
            .assertHeightIsEqualTo(150.dp).assertWidthIsAtLeast(100.dp)
    }

    @Test
    fun verifyCalendarCardDimensionsAndAlignment() {
        composeTestRule.onNodeWithTag("calendarCard").apply {
            assertHeightIsEqualTo(400.dp)
            assertIsDisplayed()
        }
    }

}