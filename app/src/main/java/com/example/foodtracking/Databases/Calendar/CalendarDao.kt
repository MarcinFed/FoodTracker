package com.example.foodtracking.Databases.Calendar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CalendarDao {
    @Query("SELECT * FROM calendar_table ORDER BY date ASC")
    fun getAllCalendarItems(): Flow<List<CalendarItem>>

    @Query("SELECT * FROM calendar_table WHERE date = :date")
    fun getCalendarItem(date: String): CalendarItem

    @Query("DELETE FROM calendar_table")
    fun deleteAll()

    @Query("DELETE FROM calendar_table WHERE date = :date")
    fun deleteItem(date: String)

    @Update
    fun update(item: CalendarItem): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: CalendarItem) : Long

}