package com.example.foodtracking.Screens.PopUp

import androidx.annotation.ColorRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.foodtracking.R

@Composable
fun ConfirmationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit, dish: String, date: String) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Confirm Deletion") },
        text = { Text(text = "Are you sure you want to delete $dish on $date") },
        confirmButton = {
            Button(onClick = { onConfirm() }) {
                Text("Delete", color = colorResource(id = R.color.blue))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel", color = Color.Black)
            }
        },
    )
}