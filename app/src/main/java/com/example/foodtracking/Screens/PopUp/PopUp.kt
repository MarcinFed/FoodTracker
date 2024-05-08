package com.example.foodtracking.Screens.PopUp

import android.app.DatePickerDialog
import android.media.MediaPlayer
import android.widget.DatePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.foodtracking.Databases.Food.Dish
import com.example.foodtracking.Databases.Food.DishRepository
import com.example.foodtracking.Databases.ShoppingList.ListItem
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import com.example.foodtracking.R
import java.util.Calendar

@Composable
fun PopUp(onDismiss: () -> Unit, listViewModel: ListViewModel, dish: Dish) {
    val context = LocalContext.current
    val (showDialog, setShowDialog) = remember { mutableStateOf(true) }
    val (date, setDate) = remember { mutableStateOf("") }
    val mMediaPlayer = MediaPlayer.create(context, R.raw.notification)

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                setShowDialog(false)
                onDismiss()
            },
            title = {
                Text(text = "Select a date for this dish")
            },
            text = {
                   Box(
                       modifier = Modifier
                           .border(
                               1.dp,
                               Color.Gray,
                               RoundedCornerShape(12.dp)
                           ) // Set rounded corners for the border
                           .clip(RoundedCornerShape(12.dp))  // Apply clipping to round the corners of the Box
                           .width(200.dp)
                   ){
                       val calendar = Calendar.getInstance()
                       Button(onClick = {
                           DatePickerDialog(
                               context,
                               { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                                   setDate("$dayOfMonth/${month + 1}/$year")
                               },
                               calendar.get(Calendar.YEAR),
                               calendar.get(Calendar.MONTH),
                               calendar.get(Calendar.DAY_OF_MONTH),
                           ).show()
                       }) {
                           Row(
                               verticalAlignment = Alignment.CenterVertically,
                               horizontalArrangement = Arrangement.Start
                           ){
                               Icon(imageVector = Icons.Outlined.DateRange,
                                   contentDescription = "Date",
                                   tint = Color.Black,
                                   modifier = Modifier.padding(end = 5.dp))
                               Text(date.ifEmpty { "Select a date" },
                                   color = Color.Black)
                           }

                       }
                   }
            },
            confirmButton = {
                Button(
                    onClick = {
                        mMediaPlayer.start()
                        for (ingredient in dish.ingredients) {
                            listViewModel.insertItem(ListItem(ingredient.name, ingredient.amount, false))
                        }
                        setShowDialog(false)
                        onDismiss()
                    }
                ) {
                    Text("Add", color = colorResource(id = R.color.blue))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        setShowDialog(false)
                        onDismiss()
                    }
                ) {
                    Text("Cancel", color = Color.Black)
                }
            }
        )
    }
}