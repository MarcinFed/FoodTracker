package com.example.foodtracking.ui.theme

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    textColor: Color = Color.Black,
    disabledTextColor: Color = Color.Gray,
    backgroundColor: Color = Color.White,
    cursorColor: Color = Color.Black,
    errorCursorColor: Color = Color.Red,
) = TextFieldDefaults.textFieldColors(
    focusedTextColor = textColor,
    unfocusedTextColor = textColor,
    disabledTextColor = disabledTextColor,
    containerColor = backgroundColor,
    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor
)