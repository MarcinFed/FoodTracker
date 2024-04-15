package com.example.foodtracking.Databases.Recipes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecipesItem::class], version = 1)
abstract class RecipesDB : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

    companion object {
        @Volatile
        private var DB_INSTANCE: RecipesDB? = null

        @Synchronized
        open fun getDatabase(context: Context): RecipesDB? {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    RecipesDB::class.java,
                    "recipes_database"
                ).allowMainThreadQueries().build()
            }
            return DB_INSTANCE
        }
    }
}