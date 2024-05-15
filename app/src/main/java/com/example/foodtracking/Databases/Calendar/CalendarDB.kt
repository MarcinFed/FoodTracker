package com.example.foodtracking.Databases.Calendar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CalendarItem::class], version = 1)
abstract class CalendarDB : RoomDatabase() {

    abstract fun calendarDao(): CalendarDao

    companion object {
        @Volatile
        private var DB_INSTANCE: CalendarDB? = null

        @Synchronized
        open fun getDatabase(context: Context): CalendarDB? {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    CalendarDB::class.java,
                    "calendar_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return DB_INSTANCE
        }
    }
}