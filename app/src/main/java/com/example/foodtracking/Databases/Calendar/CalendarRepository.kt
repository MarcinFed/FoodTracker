package com.example.foodtracking.Databases.Calendar

import kotlinx.coroutines.flow.Flow

class CalendarRepository (
    private val calendarDao: CalendarDao
) {
    fun getCalendarItems(): Flow<List<CalendarItem>> {
        return calendarDao.getAllCalendarItems()
    }

    fun getCalendarItem(date: String): CalendarItem {
        return calendarDao.getCalendarItem(date)
    }

    fun addCalendarItem(item: CalendarItem) : Boolean {
        return calendarDao.insert(item) >= 0
    }

    fun deleteCalendarItem(date: String) {
        calendarDao.deleteItem(date)
    }

    fun deleteAllCalendarItems() {
        calendarDao.deleteAll()
    }

    fun modifyCalendarItem(date: String, mealId: Int) {
        calendarDao.deleteItem(date)
        val modifiedItem = CalendarItem(date, mealId)
        calendarDao.insert(modifiedItem)
    }

    fun modifyCalendarItem(item: CalendarItem) {
        calendarDao.update(item)
    }

    fun checkIfDishExists(date: String): Boolean {
        return calendarDao.getCalendarItem(date) != null
    }

}