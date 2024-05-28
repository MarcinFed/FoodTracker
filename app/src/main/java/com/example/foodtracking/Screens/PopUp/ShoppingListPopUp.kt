package com.example.foodtracking.Screens.PopUp

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.foodtracking.R

@Composable
fun shoppingListItemsDelete(showDialog: Boolean, onDismiss: () -> Unit, confirmAction: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = stringResource(id = R.string.confirm_delete))
            },
            text = {
                Text(text = stringResource(id = R.string.delete_text))
            },
            confirmButton = {
                TextButton(
                    onClick = confirmAction,
                    modifier = Modifier.testTag("confirm_delete")
                ) {
                    Text(
                        stringResource(id = R.string.confirm),
                        color = colorResource(id = R.color.blue)
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss

                ) {
                    Text(
                        stringResource(id = R.string.cancel),
                        color = Color.Black
                    )
                }
            }
        )
    }
}