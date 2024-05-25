package com.example.foodtracking

import androidx.activity.compose.setContent
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.test.espresso.Espresso
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
            .performImeAction()
        composeTestRule.onNodeWithText("Pear 3.0kg")
            .assertExists()
    }

    @Test
    fun SelectAllItemsOnShoppingList() {
        composeTestRule.onNodeWithTag("Shopping List", useUnmergedTree = true)
            .assertExists()
            .performClick()

    }
}
