package com.example.foodtracking.Screens.Fab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

sealed class FabButtonState {
    object Collapsed : FabButtonState()
    object Expand : FabButtonState()

    fun isExpanded() = this == Expand

    fun toggleValue() = if (isExpanded()) {
        Collapsed
    } else {
        Expand
    }
}

@Composable
fun rememberMultiFabState() =
    remember { mutableStateOf<FabButtonState>(FabButtonState.Collapsed) }