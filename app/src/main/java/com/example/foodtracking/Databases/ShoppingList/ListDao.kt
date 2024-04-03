package com.example.foodtracking.Databases.ShoppingList

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {
    @Query("SELECT * FROM list_table ORDER BY id ASC")
    fun getAllData(): Flow<List<ListItem>>

    @Query("DELETE FROM list_table")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: ListItem) : Long

    @Delete
    fun delete(item: ListItem) : Int

    @Update
    fun update(item: ListItem): Int

}