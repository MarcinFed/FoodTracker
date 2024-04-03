package com.example.foodtracking.Databases.ShoppingList

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListItem::class], version = 1)
abstract class ListDB : RoomDatabase() {

    abstract fun listDao(): ListDao

    companion object {
        @Volatile
        private var DB_INSTANCE: ListDB? = null

        @Synchronized
        open fun getDatabase(context: Context): ListDB? {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ListDB::class.java,
                    "list_database"
                ).allowMainThreadQueries().build()
            }
            return DB_INSTANCE
        }
    }
}