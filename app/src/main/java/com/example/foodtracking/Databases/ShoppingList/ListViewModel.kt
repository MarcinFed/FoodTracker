package com.example.foodtracking.Databases.ShoppingList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ListViewModel(
    application: Application,
    listDB: ListDB?
): AndroidViewModel(application) {
    private var listRepository: ListRepository = ListRepository(listDB!!.listDao())

    fun getAllItems() = listRepository.getData()
    fun insertItem(item: ListItem) = viewModelScope.launch {
        listRepository.addItem(item)
    }
    fun deleteItem(item: ListItem) = viewModelScope.launch {
        listRepository.deleteItem(item)
    }
    fun modifyItem(id: Int, productName: String, amount: Int, bought: Boolean) = viewModelScope.launch {
        listRepository.modifyItem(id, productName, amount, bought)
    }
    fun checkItem(id: Int, bought: Boolean) = viewModelScope.launch{
        listRepository.checkItem(id, bought)
    }
    fun checkAllItems(bought: Boolean) = viewModelScope.launch {
        listRepository.checkAllItems(bought)
    }
    fun deleteCheckedItems() = viewModelScope.launch {
        listRepository.deleteCheckedItems()
    }
}

class ListViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(
                application = application,
                listDB = ListDB.getDatabase(application)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}