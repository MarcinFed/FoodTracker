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

    fun modifyItem(id: Int, Product: String, Amount: Float, Bought: Boolean) {
        val modifiedItem = ListItem(Product, Amount, Bought)
        modifiedItem.id = id
        listDao?.update(modifiedItem)
    }

    fun checkItem(id: Int, Bought: Boolean) {
        val item = listDao?.getItem(id)
        item?.Bought = Bought
        listDao?.update(item!!)
    }

    fun checkAllItems(bought: Boolean){
        listDao.checkAllItems(bought)
    }

    fun deleteCheckedItems(){
        listDao.deleteCheckedItems()
    }

    fun itemsChecked() : Boolean{
        if (listDao.itemsChecked() > 0)
            return true
        return false
    }
}