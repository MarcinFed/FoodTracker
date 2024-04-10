package com.example.foodtracking.Screens.Keyboard

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun rememberKeyboardVisibilityState(): Boolean {
    var keyboardOpened by remember { mutableStateOf(false) }
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val activity = context.getActivity()
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            activity?.window?.decorView?.getWindowVisibleDisplayFrame(rect)
            val screenHeight = activity!!.window.decorView.height
            val keypadHeight = screenHeight - rect.bottom

            // Update state based on keyboard visibility
            keyboardOpened = keypadHeight > screenHeight * 0.15
        }

        activity?.window?.decorView?.viewTreeObserver?.addOnGlobalLayoutListener(listener)

        // Cleanup function to be called on dispose
        onDispose {
            activity?.window?.decorView?.viewTreeObserver?.removeOnGlobalLayoutListener(listener)
        }
    }

    return keyboardOpened
}

// Helper function to get Activity from Context
fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}