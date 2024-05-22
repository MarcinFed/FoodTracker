package com.example.foodtracking.Databases.ShoppingList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel(
    application: Application,
    listDB: ListDB?
): AndroidViewModel(application) {
    private var listRepository: ListRepository = ListRepository(listDB!!.listDao())
    var allItems = listRepository.getData().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private fun formatProductName(name: String): String {
        return name.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
    fun getAllItems() {
        allItems = listRepository.getData().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    }
    fun insertItem(item: ListItem) = viewModelScope.launch {
        listRepository.addItem(item)
    }
    fun deleteItem(item: ListItem) = viewModelScope.launch {
        listRepository.deleteItem(item)
    }
    fun modifyItem(productName: String, amount: Float, unit: String, bought: Boolean) = viewModelScope.launch {
        val formattedName = formatProductName(productName)
        listRepository.modifyItem(formattedName, amount, unit, bought)
        getAllItems()
    }

    fun substractItem(productName: String, amount: Float, unit: String, bought: Boolean) = viewModelScope.launch {
        val formattedName = formatProductName(productName)
        listRepository.substractItem(formattedName, amount, unit, bought)
    }
    fun addItem(productName: String, amount: Float, unit: String, bought: Boolean) = viewModelScope.launch {
        val formattedName = formatProductName(productName)
        listRepository.addItem(formattedName, amount, unit, bought)
        getAllItems()
    }
    fun checkItem(productName: String, bought: Boolean) = viewModelScope.launch{
        listRepository.checkItem(productName, bought)
    }
    fun checkAllItems(bought: Boolean) = viewModelScope.launch {
        listRepository.checkAllItems(bought)
    }
    fun deleteCheckedItems() = viewModelScope.launch {
        listRepository.deleteCheckedItems()
    }

    fun itemsChecked() : Boolean {
        return listRepository.itemsChecked()
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