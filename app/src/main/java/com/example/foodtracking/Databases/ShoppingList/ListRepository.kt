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

    fun substractItem(productName: String, amount: Float, metrics: String, bought: Boolean) {
        val substractedItem = ListItem(productName, listDao.getItem(productName).Amount - amount, metrics, bought)
        if (substractedItem.Amount <= 0)
            listDao?.delete(substractedItem)
        else
            listDao?.update(substractedItem)
    }

    fun addItem(productName: String, amount: Float, metrics: String, bought: Boolean) {
        val item = ListItem(productName, amount, metrics, bought)
        if (listDao.getItem(productName) == null)
            listDao.insert(item)
        else
            substractItem(productName, -amount, metrics, bought)
    }


    fun modifyItem(product: String, amount: Float, metrics: String, bought: Boolean) {
        val modifiedItem = ListItem(product, amount, metrics, bought)
        listDao?.update(modifiedItem)
    }

    fun checkItem(product: String, bought: Boolean) {
        val item = listDao?.getItem(product)
        item?.Bought = bought
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