package com.example.foodtracking.Navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource

@Composable
fun FlipIcon(
    isSelected: Boolean,
    activeIcon: Int,
    inactiveIcon: Int,
    modifier: Modifier = Modifier
) {
    val animationRotation by animateFloatAsState(
        targetValue = if (isSelected) 180f else 0f,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        // Use Image instead of Icon for bitmap images
        Image(
            painter = painterResource(id = if (isSelected) activeIcon else inactiveIcon),
            contentDescription = null, // Provide appropriate content description.
            modifier = Modifier.graphicsLayer {
                rotationY = animationRotation
                cameraDistance = 12f * density // Adjust for better 3D effect.
            }
        )
    }
}
