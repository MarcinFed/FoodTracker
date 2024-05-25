package com.example.foodtracking

import ConfirmDishChangeDialog
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foodtracking.ui.theme.FoodTrackingTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OverrideDishKtTest{

    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            FoodTrackingTheme {
                ConfirmDishChangeDialog(
                    onDismiss = {},
                    onConfirm = {},
                    date = "2022-01-01",
                    oldDishName = "Old Dish",
                    newDishName = "New Dish"
                )
            }
        }
    }

    @Test
    fun verifyIfAllViewsExists(){
        composeTestRule.onNodeWithTag("TitleConfirmChange").assertIsDisplayed()
        composeTestRule.onNodeWithTag("TextConfirmChange").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ButtonConfirm").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ButtonCancel").assertIsDisplayed()
    }

    @Test
    fun verifyIfTextIsCorrect(){
        composeTestRule.onNodeWithTag("TextConfirmChange").assertTextEquals("Are you sure you want to change dish from Old Dish to New Dish on 2022-01-01?")
    }

    @Test
    fun verifyIfButtonIsCorrect(){
        composeTestRule.onNodeWithTag("TextConfirm", useUnmergedTree = true).assertTextEquals("Confirm")
        composeTestRule.onNodeWithTag("TextCancel", useUnmergedTree = true).assertTextEquals("Cancel")
    }

    @Test
    fun verifyIfTitleIsCorrect(){
        composeTestRule.onNodeWithTag("TitleConfirmChange").assertTextEquals("Confirm Change")
    }

    @Test
    fun verifyIfConfirmButtonIsClickable(){
        composeTestRule.onNodeWithTag("ButtonConfirm").performClick()
    }

    @Test
    fun verifyIfCancelButtonIsClickable(){
        composeTestRule.onNodeWithTag("ButtonCancel").performClick()
    }
}