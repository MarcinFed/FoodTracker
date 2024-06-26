package com.example.foodtracking.Screens.Fab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

interface FabButtonMain {
    val iconRes: ImageVector
    val iconRotate: Float?
}

private class FabButtonMainImpl(
    override val iconRes: ImageVector,
    override val iconRotate: Float?
) : FabButtonMain

fun FabButtonMain(iconRes: ImageVector = Icons.Filled.Settings, iconRotate: Float = 360f): FabButtonMain =
    FabButtonMainImpl(iconRes, iconRotate)