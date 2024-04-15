package com.example.foodtracking.Databases.Recipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecipesViewModel(
    application: Application,
    recipesDB: RecipesDB?
): AndroidViewModel(application) {
    private var recipesRepository: RecipesRepository = RecipesRepository(recipesDB!!.recipesDao())

    fun getAllItems() = recipesRepository.getData()
    fun getItem(id: Int) = recipesRepository.getItem(id)
}

class RecipesViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipesViewModel(
                application = application,
                recipesDB = RecipesDB.getDatabase(application)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}