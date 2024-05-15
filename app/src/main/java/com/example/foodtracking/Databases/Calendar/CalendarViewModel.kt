package com.example.foodtracking.Databases.Calendar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CalendarViewModel(
    application: Application,
    calendarDB: CalendarDB?
): AndroidViewModel(application) {
    private var calendarRepository: CalendarRepository =
        CalendarRepository(calendarDB!!.calendarDao())

    fun getCalendarItems() = calendarRepository.getCalendarItems()

    fun getCalendarItem(date: String): CalendarItem? {
        return calendarRepository.getCalendarItem(date)
    }

    fun addCalendarItem(item: CalendarItem) = viewModelScope.launch {
        calendarRepository.addCalendarItem(item)
    }

    fun addCalendarItem(date: String, dish: Int) = viewModelScope.launch {
        val item = CalendarItem(date, dish)
        calendarRepository.addCalendarItem(item)
    }

    fun deleteCalendarItem(date: String) = viewModelScope.launch {
        calendarRepository.deleteCalendarItem(date)
    }

    fun deleteAllCalendarItems() = viewModelScope.launch {
        calendarRepository.deleteAllCalendarItems()
    }

    fun modifyCalendarItem(date: String, mealId: Int) = viewModelScope.launch {
        calendarRepository.modifyCalendarItem(date, mealId)
    }

    fun modifyCalendarItem(item: CalendarItem) = viewModelScope.launch {
        calendarRepository.modifyCalendarItem(item)
    }

    fun checkIfDishExists(date: String): Boolean {
        return calendarRepository.checkIfDishExists(date)
    }
}

class CalendarViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalendarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CalendarViewModel(
                application = application,
                calendarDB = CalendarDB.getDatabase(application)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}