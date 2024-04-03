package com.example.foodtracking.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import com.example.foodtracking.ui.theme.FoodTrackingTheme

@Composable
fun ShoppingListScreen(listViewModel: ListViewModel) {
    var shoppingList by remember { mutableStateOf(listOf("Milk", "Bread", "Cheese x2", "Chicken", "Garlic")) }
    var newText by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    FoodTrackingTheme {
        Surface(
            modifier = Modifier.fillMaxSize().padding(15.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                LazyColumn {
                    itemsIndexed(shoppingList) { index, item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = false,
                                onCheckedChange = {}
                            )
                            Box(
                                modifier = Modifier.fillMaxWidth().height(55.dp)
                                    .padding(start = 8.dp, end = 12.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                OutlinedTextField(
                                    value = item,
                                    onValueChange = { updatedItem ->
                                        // Update the item at this index
                                        shoppingList = shoppingList.toMutableList().apply {
                                            this[index] = updatedItem
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .height(55.dp),
                                    singleLine = true,
                                    textStyle = MaterialTheme.typography.labelLarge.copy(
                                        color = Color.Black,
                                        textAlign = TextAlign.Start
                                    ),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        cursorColor = Color.Black,

                                    ),
                                )
                            }
                        }
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = false,
                                onCheckedChange = {}
                            )
                            Box(
                                modifier = Modifier.fillMaxWidth().height(55.dp)
                                    .padding(start = 8.dp, end = 12.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                OutlinedTextField(
                                    value = newText,
                                    onValueChange = { newText = it },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .height(55.dp),
                                    placeholder = { Text("Add new item") },
                                    singleLine = true,
                                    textStyle = MaterialTheme.typography.labelLarge.copy(
                                        color = Color.Black,
                                        textAlign = TextAlign.Start
                                    ),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        cursorColor = Color.Black,
                                    ),
                                    keyboardActions = KeyboardActions(onDone = {
                                        keyboardController?.hide()
                                        if (newText.isNotBlank()) {
                                            shoppingList = shoppingList + newText
                                            newText = ""
                                        }
                                    })
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

