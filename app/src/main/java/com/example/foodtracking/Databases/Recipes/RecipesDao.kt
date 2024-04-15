package com.example.foodtracking.Databases.Recipes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun getAllData(): Flow<List<RecipesItem>>

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insert(item: RecipesItem) : Long

    @Query("SELECT * FROM recipes_table WHERE id = :id")
    fun getItem(id: Int): RecipesItem
}