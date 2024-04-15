package com.example.foodtracking.Databases.Recipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ListViewModel(
    application: Application,
    listDB: RecipesDB?
): AndroidViewModel(application) {
    private var listRepository: RecipesRepository = RecipesRepository(listDB!!.listDao())

    fun getAllItems() = listRepository.getData()
    fun getItem(id: Int) = listRepository.getItem(id)
}

class ListViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(
                application = application,
                listDB = RecipesDB.getDatabase(application)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}