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
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
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
import com.example.foodtracking.Databases.ShoppingList.ListItem
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import com.example.foodtracking.ui.theme.FoodTrackingTheme
import com.example.foodtracking.ui.theme.MyTextField

@Composable
fun ShoppingListScreen(listViewModel: ListViewModel) {
    val shoppingList by listViewModel.getAllItems()!!.collectAsState(initial = emptyList())
    var newText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    val customTextSelectionColors = TextSelectionColors(
        handleColor = Color.Black,
        backgroundColor = Color(0xFFBEBEBE),
    )

        FoodTrackingTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                Column {
                    LazyColumn {
                        itemsIndexed(shoppingList) { index, item ->
                            var itemText by remember {
                                mutableStateOf(
                                    if (item.Amount > 1) "${item.Product} X ${item.Amount}" else item.Product
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Color(0xFF1683FB),
                                    ),
                                    checked = item.Bought,
                                    onCheckedChange = { isChecked ->
                                        listViewModel.checkItem(item.id, isChecked)
                                    }
                                )
                                CompositionLocalProvider(
                                    LocalTextSelectionColors provides customTextSelectionColors,
                                ) {
                                    OutlinedTextField(
                                        colors = MyTextField(),
                                        value = itemText,
                                        onValueChange = { updatedText ->
                                            itemText = updatedText
                                            // Update your model when text changes, if needed
                                            // This is a simplified example. In a real app, you'd likely need to parse
                                            // the updatedText back into Product and Amount, then call modifyItem.
                                        },
                                        modifier = Modifier
                                            .padding(start = 8.dp, end = 12.dp)
                                            .weight(1f)
                                            .clip(RoundedCornerShape(8.dp))
                                            .border(
                                                width = 1.dp,
                                                color = Color.Gray,
                                                shape = RoundedCornerShape(8.dp)),
                                        textStyle = MaterialTheme.typography.bodyLarge,
                                        singleLine = true,
                                        keyboardActions = KeyboardActions(onDone = {
                                            keyboardController?.hide()
                                            // Assuming modifyItem updates both the product name and the amount.
                                            // You'll need to extract the product name and amount from itemText.
                                            // Here's a placeholder for the extraction logic.
                                            val productName =
                                                itemText // Placeholder, extract actual name
                                            val amount = 1 // Placeholder, extract actual amount
                                            listViewModel.modifyItem(
                                                item.id,
                                                productName,
                                                amount,
                                                item.Bought
                                            )
                                        })
                                    )
                                }
                            }
                        }
                    }
                    CompositionLocalProvider(
                        LocalTextSelectionColors provides customTextSelectionColors,
                    ) {
                        OutlinedTextField(
                            colors = MyTextField(),
                            value = newText,
                            onValueChange = { newText = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(8.dp)),
                            placeholder = { Text("Add new item") },
                            singleLine = true,
                            textStyle = MaterialTheme.typography.bodyLarge,
                            keyboardActions = KeyboardActions(onDone = {
                                keyboardController?.hide()
                                if (newText.isNotBlank()) {
                                    // Assuming ListItem constructor accepts product name, amount, and bought status.
                                    listViewModel.insertItem(
                                        ListItem(
                                            newText,
                                            1,
                                            false
                                        )
                                    ) // Adjust parameters as necessary.
                                    newText = ""
                                }
                            })
                        )
                    }
                }
            }
        }
    }

