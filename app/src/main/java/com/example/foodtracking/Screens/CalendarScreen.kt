package com.example.foodtracking.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.foodtracking.Databases.Calendar.CalendarViewModel
import com.example.foodtracking.Databases.Food.DishRepository
import com.example.foodtracking.Navigation.Screen
import com.example.foodtracking.R
import com.example.foodtracking.ui.theme.FoodTrackingTheme
import kotlinx.coroutines.launch

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    navController: NavController,
    calendarViewModel: CalendarViewModel
) {
    val datePickerState = rememberDatePickerState()
    val coroutineScope = rememberCoroutineScope()

    val selectedDate = datePickerState.selectedDateMillis?.let {
        java.text.SimpleDateFormat("dd/MM/yyyy").format(it)
    }
    var mealId by remember { mutableStateOf(-1) }

    LaunchedEffect(selectedDate) {
        selectedDate?.let { date ->
            coroutineScope.launch {
                val calendarItem = calendarViewModel.getCalendarItem(date)
                mealId = calendarItem?.mealId ?: -1
            }
        }
    }

    Column() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 27.dp)
                .height(400.dp),
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
            )
        }
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 27.dp),
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
                    Log.println(Log.ERROR, "Dish", mealId.toString())
                    navController.navigate(Screen.DishDetailScreen.route + "/${mealId}")
                }
            }
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                if (mealId == -1) {
                    Text(
                        text = "No dish for this date",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                } else {
                    Column() {
                        Text(
                            text = "Dish for this date",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = androidx.compose.ui.text.font.FontWeight(500),
                            color = Color.Black
                        )
                        DishRepository.getDish(mealId)?.let { dish ->
                            Column()
                            {
                                Spacer(modifier = Modifier.size(16.dp))
                                Image(
                                    painter = rememberImagePainter(dish.imageRes),
                                    contentDescription = "Dish Image",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .clip(RoundedCornerShape(16.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    dish.name,
                                    color = Color.Black,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = androidx.compose.ui.text.font.FontWeight(500)
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                Text(
                                    dish.description,
                                    color = Color.Black,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

