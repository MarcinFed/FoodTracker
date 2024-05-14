package com.example.foodtracking.Screens

import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodtracking.R
import com.example.foodtracking.ui.theme.FoodTrackingTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    navController: NavController
    //, CalendarViewModel: CalendarViewModel
) {
    val datePickerState = rememberDatePickerState()
    FoodTrackingTheme {
        Surface(
            modifier = Modifier
                .height(400.dp)
                .padding(15.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 12.dp)
                    .heightIn(max = 300.dp),
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
        }
    }
}