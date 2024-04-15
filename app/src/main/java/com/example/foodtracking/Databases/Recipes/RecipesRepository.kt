package com.example.foodtracking.Databases.Recipes

import kotlinx.coroutines.flow.Flow

class RecipesRepository (
    private val listDao: RecipesDao
) {
    fun getData(): Flow<List<RecipesItem>>? {
        return listDao?.getAllData()
    }

    fun getItem(id :Int): RecipesItem? {
        return listDao?.getItem(id)
    }
}