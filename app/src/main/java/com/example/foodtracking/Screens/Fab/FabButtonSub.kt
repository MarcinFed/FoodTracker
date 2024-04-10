package com.example.foodtracking.Screens.Fab

import androidx.compose.ui.graphics.Color

interface FabButtonSub {
    val iconTint: Color
    val backgroundTint: Color
}

private class FabButtonSubImpl(
    override val iconTint: Color,
    override val backgroundTint: Color
) : FabButtonSub

fun FabButtonSub(
    backgroundTint: Color = Color(0xFF1683FB),
    iconTint: Color = Color.White
): FabButtonSub = FabButtonSubImpl(iconTint, backgroundTint)