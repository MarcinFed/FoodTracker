package com.example.foodtracking

import androidx.activity.compose.setContent
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foodtracking.ui.theme.FoodTrackingTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EndToEndTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.activity.setContent {
            FoodTrackApp()
        }
    }

    @Test
    fun creatingAndSavingItemOnShoppingList() {
        composeTestRule.onNodeWithTag("Shopping List", useUnmergedTree = true)
            .assertExists()
            .performClick()
        val inputField = composeTestRule.onNodeWithTag("AddNewItemTextField")
        inputField
            .assertExists()
            .performTextInput("Tomato 2")
        inputField
            .performImeAction()
        composeTestRule.onNodeWithText("Tomato x2.0")
            .assertExists()
    }

    @Test
    fun creatingAndAddingTheSameItemOnShoppingList() {
        composeTestRule.onNodeWithTag("Shopping List", useUnmergedTree = true)
            .assertExists()
            .performClick()
        val inputField = composeTestRule.onNodeWithTag("AddNewItemTextField")
        inputField
            .assertExists()
            .performTextInput("Apple 2")
        inputField
            .performImeAction()
        composeTestRule.onNodeWithText("Apple x2.0")
            .assertExists()
        composeTestRule.onNodeWithText("Apple x2.0")
            .performClick()
        val editField = composeTestRule.onNodeWithTag("AddNewItemTextField")
        editField
            .assertExists()
            .performTextInput("Apple 3")
        editField
            .performImeAction()
        composeTestRule.waitUntil { composeTestRule.onNodeWithText("Apple x5.0").isDisplayed() }
        composeTestRule.onNodeWithText("Apple x5.0")
            .assertExists()
    }

    @Test
    fun creatingAndEditingItemOnShoppingList() {
        composeTestRule.onNodeWithTag("Shopping List", useUnmergedTree = true)
            .assertExists()
            .performClick()
        val inputField = composeTestRule.onNodeWithTag("AddNewItemTextField")
        inputField
            .assertExists()
            .performTextInput("Pear 2")
        inputField
            .performImeAction()
        composeTestRule.onNodeWithText("Pear x2.0")
            .assertExists()
        composeTestRule.onNodeWithText("Pear x2.0")
            .performClick()
        val editField = composeTestRule.onNodeWithText("Pear x2.0")
        editField
            .assertExists()
            .performTextReplacement("Pear 3kg")
        composeTestRule.onNodeWithText("Pear 3kg")
            .assertExists()
            .performImeAction()
        composeTestRule.waitUntil { composeTestRule.onNodeWithText("Pear 3.0kg").isDisplayed() }
        composeTestRule.onNodeWithText("Pear 3.0kg")
            .assertIsDisplayed()
            .assertExists()
    }

    @Test
    fun selectAndDeleteAllItemsOnShoppingList() {
        composeTestRule.onNodeWithTag("Shopping List", useUnmergedTree = true)
            .assertExists()
            .performClick()
        composeTestRule.onNodeWithTag("main_fab_button")
            .assertExists()
            .performClick()
        composeTestRule.onNodeWithTag("select_all")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("delete_checked")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("confirm_delete")
            .assertExists()
            .performClick()
        composeTestRule.onNodeWithTag("delete_checked")
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithText("Tomato x2.0")
            .assertDoesNotExist()
    }

    @Test
    fun selectThenUnselectAndTryToDeleteAllItemsOnShoppingList() {
        composeTestRule.onNodeWithTag("Shopping List", useUnmergedTree = true)
            .assertExists()
            .performClick()
        val inputField = composeTestRule.onNodeWithTag("AddNewItemTextField")
        inputField
            .assertExists()
            .performTextInput("Juice")
        inputField
            .performImeAction()
        composeTestRule.onNodeWithText("Juice")
            .assertExists()
        composeTestRule.onNodeWithTag("main_fab_button")
            .assertExists()
            .performClick()
        composeTestRule.onNodeWithTag("select_all")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("unselect_all")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("main_fab_button")
            .assertExists()
            .performClick()
        composeTestRule.onNodeWithTag("delete_checked")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("delete_checked")
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithText("Juice")
            .assertExists()
    }
}
