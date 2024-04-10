package com.example.foodtracking.Screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodtracking.Databases.ShoppingList.ListItem
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import com.example.foodtracking.R
import com.example.foodtracking.Screens.Fab.FabButtonItem
import com.example.foodtracking.Screens.Fab.FabButtonMain
import com.example.foodtracking.Screens.Fab.FabButtonState
import com.example.foodtracking.Screens.Fab.FabButtonSub
import com.example.foodtracking.Screens.Fab.MultiFloatingActionButton
import com.example.foodtracking.Screens.Fab.rememberMultiFabState
import com.example.foodtracking.Screens.Keyboard.rememberKeyboardVisibilityState
import com.example.foodtracking.ui.theme.FoodTrackingTheme
import com.example.foodtracking.ui.theme.MyTextField

@SuppressLint("SuspiciousIndentation", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShoppingListScreen(listViewModel: ListViewModel) {
    val shoppingList by listViewModel.getAllItems()!!.collectAsState(initial = emptyList())
    var newText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val fabState = rememberMultiFabState()
    val keyboardOpened = rememberKeyboardVisibilityState()

    val customTextSelectionColors = TextSelectionColors(
        handleColor = Color.Black,
        backgroundColor = Color(0xFFBEBEBE),
    )

    val overlayModifier = if (fabState.value == FabButtonState.Expand) {
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    // This should set the state to Collapsed when the overlay is tapped
                    fabState.value = FabButtonState.Collapsed
                })
            }
    } else Modifier

    if (keyboardOpened) {
        fabState.value = FabButtonState.Collapsed
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            MultiFloatingActionButton(
                items = listOf(
                    FabButtonItem(
                        iconRes = Icons.Filled.Check,
                        label = stringResource(id = R.string.select),
                        id = 0
                    ),
                    FabButtonItem(
                        iconRes = Icons.Filled.Refresh,
                        label = stringResource(id = R.string.unselect),
                        id = 1
                    ),
                    FabButtonItem(
                        iconRes = Icons.Filled.Delete,
                        label = stringResource(id = R.string.delete),
                        id = 2
                    ),
                ),
                onFabItemClicked = { item ->
                    when (item.id) {
                        0 -> {
                            listViewModel.checkAllItems(bought = true)
                        }
                        1 -> {
                            listViewModel.checkAllItems(bought = false)
                        }
                        2 -> {
                            listViewModel.deleteCheckedItems()
                        }
                        else -> Toast.makeText(context, item.label, Toast.LENGTH_SHORT).show()
                    }
                },
                fabIcon = FabButtonMain(),
                fabOption = FabButtonSub(),
                fabState = fabState
            )
        }
    ) {
        Box(modifier = overlayModifier){
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
                                        if (item.Amount > 1) "${item.Product} X${item.Amount}" else item.Product
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
                                            fabState.value = FabButtonState.Collapsed
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
                                            },
                                            modifier = Modifier
                                                .padding(start = 8.dp, end = 12.dp)
                                                .weight(1f)
                                                .clip(RoundedCornerShape(8.dp))
                                                .border(
                                                    width = 1.dp,
                                                    color = Color.Gray,
                                                    shape = RoundedCornerShape(8.dp)
                                                ),
                                            textStyle = MaterialTheme.typography.bodyLarge,
                                            singleLine = true,
                                            keyboardActions = KeyboardActions(onDone = {
                                                keyboardController?.hide()
                                                val (productName, amount) = parseItemInput(itemText)
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
                            item {
                                CompositionLocalProvider(
                                    LocalTextSelectionColors provides customTextSelectionColors,
                                ) {
                                    OutlinedTextField(
                                        colors = MyTextField(),
                                        value = newText,
                                        onValueChange = { newText = it },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                start = 8.dp,
                                                end = 12.dp,
                                                top = 8.dp,
                                                bottom = 8.dp
                                            )
                                            .clip(RoundedCornerShape(8.dp))
                                            .border(
                                                width = 1.dp,
                                                color = Color.Gray,
                                                shape = RoundedCornerShape(8.dp)
                                            ),
                                        placeholder = { Text("Add new item") },
                                        singleLine = true,
                                        textStyle = MaterialTheme.typography.bodyLarge,
                                        keyboardActions = KeyboardActions(onDone = {
                                            keyboardController?.hide()
                                            if (newText.isNotBlank()) {
                                                val (productName, amount) = parseItemInput(newText)
                                                listViewModel.insertItem(
                                                    ListItem(productName, amount, false)
                                                )
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
}

fun parseItemInput(input: String): Pair<String, Int> {
    val regex = Regex("^(.+?)\\s*[xX](\\d+)$|^(.+?)\\s+(\\d+)$")
    val matchResult = regex.find(input)

    return if (matchResult != null) {
        // Check which group is matched for the quantity
        val productName = matchResult.groups[1]?.value ?: matchResult.groups[3]?.value ?: ""
        val quantityString = matchResult.groups[2]?.value ?: matchResult.groups[4]?.value ?: "1"
        productName.trim() to quantityString.toInt()
    } else {
        input.trim() to 1 // Default quantity is 1 if not specified
    }
}
