package com.example.foodtracking.Databases.ShoppingList

import kotlinx.coroutines.flow.Flow

class ListRepository (
    private val listDao: ListDao
) {
    fun getData(): Flow<List<ListItem>>? {
        return listDao?.getAllData()
    }

    fun addItem(item: ListItem) : Boolean {
        return listDao?.insert(item)!! >= 0
    }

    fun deleteItem(item: ListItem) : Boolean {
        return listDao?.delete(item)!! > 0
    }

    fun modifyItem(id: Int, Product: String, Amount: Int, Bought: Boolean) {
        val modifiedItem = ListItem(Product, Amount, Bought)
        modifiedItem.id = id
        listDao?.update(modifiedItem)
    }
}