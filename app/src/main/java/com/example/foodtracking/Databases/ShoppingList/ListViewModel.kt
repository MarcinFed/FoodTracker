package com.example.foodtracking.Databases.ShoppingList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListViewModel(
    application: Application,
    listDB: ListDB?
): AndroidViewModel(application) {
    private var listRepository: ListRepository = ListRepository(listDB!!.listDao())

    fun getAllItems() = listRepository.getData()
    fun insertItem(item: ListItem) = listRepository.addItem(item)
    fun deleteItem(item: ListItem) = listRepository.deleteItem(item)
    fun modifyItem(id: Int, productName: String, amount: Int, bought: Boolean) = listRepository.modifyItem(id, productName, amount, bought)
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