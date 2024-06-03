package com.example.foodtracking.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.foodtracking.Databases.Calendar.CalendarViewModel
import com.example.foodtracking.Databases.Food.DishRepository
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import com.example.foodtracking.Navigation.Screen
import com.example.foodtracking.R
import com.example.foodtracking.Screens.PopUp.ConfirmationDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    navController: NavController?,
    calendarViewModel: CalendarViewModel,
    listViewModel: ListViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val today = remember { SimpleDateFormat("dd/MM/yyyy").format(Date()) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = Date().time)
    var mealId by remember { mutableIntStateOf(-1) }
    var date by remember { mutableStateOf(today) }

    LaunchedEffect(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let { dateMillis ->
            date = SimpleDateFormat("dd/MM/yyyy").format(dateMillis)
            coroutineScope.launch {
                val calendarItem = calendarViewModel.getCalendarItem(date)
                mealId = calendarItem?.mealId ?: -1
            }
        }
    }

    Column {
        CalendarCard(datePickerState)
        val selectedDateDisplay = datePickerState.selectedDateMillis?.let {
            SimpleDateFormat("MMM dd", Locale.ENGLISH).format(it)
        } ?: "selected day"

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 27.dp, end = 27.dp, top = 8.dp, bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 7.dp,
                pressedElevation = 7.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = {
                coroutineScope.launch {
                    if (mealId != -1) {
                        navController!!.navigate(Screen.DishDetailScreen.route + "/$mealId")
                    }
                }
            }
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                if (mealId == -1) {
                    Text(
                        text = "You haven't yet selected a dish for $selectedDateDisplay",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight(500),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("noDishSelected"),
                        color = Color.Black
                    )
                } else {
                    var showDialog by remember { mutableStateOf(false) }
                    if (showDialog) {
                        ConfirmationDialog(
                            onConfirm = {
                                calendarViewModel.deleteCalendarItem(date)
                                val dish = DishRepository.getDish(mealId)
                                dish?.ingredients?.forEach { ingredient ->
                                    listViewModel.substractItem(
                                        ingredient.name,
                                        ingredient.amount,
                                        ingredient.unit,
                                        false
                                    )
                                }
                                mealId = -1
                                showDialog = false
                            },
                            onDismiss = { showDialog = false },
                            dish = DishRepository.getDish(mealId)?.name ?: "",
                            date = selectedDateDisplay
                        )
                    }

                    DishRepository.getDish(mealId)?.let { dish ->
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Dish for $selectedDateDisplay",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight(500),
                                    color = Color.Black,
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("dishForDate"),
                                )
                                IconButton(onClick = { showDialog = true }, modifier = Modifier.testTag("moreOptions")) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.Black,
                                        modifier = Modifier.testTag("deleteDish")
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.size(16.dp).testTag("spacer1"))
                            Image(
                                painter = rememberImagePainter(dish.imageRes),
                                contentDescription = "Dish Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .testTag("dishImage"),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.size(8.dp).testTag("spacer2"))
                            Text(
                                dish.name,
                                color = Color.Black,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight(500),
                                modifier = Modifier.testTag("dishName")
                            )
                            Spacer(modifier = Modifier.size(16.dp).testTag("spacer3"))
                            Text(
                                dish.description,
                                color = Color.Black,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.testTag("dishDescription")
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarCard(
    datePickerState: DatePickerState
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 27.dp)
            .height(400.dp)
            .testTag("calendarCard"),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 7.dp,
            pressedElevation = 7.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = false,
            title = null,
            headline = null,
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
                todayContentColor = colorResource(id = R.color.blue),
                dayContentColor = Color.Black,
                todayDateBorderColor = colorResource(id = R.color.blue),
                selectedDayContentColor = Color.White,
                selectedDayContainerColor = colorResource(id = R.color.blue),
            ),
            modifier = Modifier.testTag("datePicker")
        )
    }
}
