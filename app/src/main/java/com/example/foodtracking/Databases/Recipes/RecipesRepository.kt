package com.example.foodtracking.Databases.Recipes

import kotlinx.coroutines.flow.Flow

class RecipesRepository (
    private val recipesDao: RecipesDao
) {
    fun getData(): Flow<List<RecipesItem>>? {
        return recipesDao?.getAllData()
    }

    fun getItem(id :Int): RecipesItem? {
        return recipesDao?.getItem(id)
    }
}