package com.example.foodtracking.Databases.ShoppingList

import kotlinx.coroutines.flow.Flow

class ListRepository (
    private val listDao: ListDao
) {
    fun getData(): Flow<List<ListItem>> {
        return listDao.getAllData()
    }

    fun addItem(item: ListItem) : Boolean {
        return listDao.insert(item) >= 0
    }

    fun deleteItem(item: ListItem) : Boolean {
        return listDao.delete(item) > 0
    }

    fun substractItem(productName: String, amount: Float, unit: String, bought: Boolean) {
        val substractedItem = ListItem(productName, listDao.getItem(productName).Amount - amount, unit, bought)
        if (substractedItem.Amount <= 0)
            listDao.delete(substractedItem)
        else
            listDao.update(substractedItem)
    }

    fun addItem(productName: String, amount: Float, unit: String, bought: Boolean) {
        val item = ListItem(productName, amount, unit, bought)
        if (listDao.getItem(productName) == null)
            listDao.insert(item)
        else
            substractItem(productName, -amount, unit, bought)
    }


    fun modifyItem(product: String, amount: Float, unit: String, bought: Boolean) {
        val modifiedItem = ListItem(product, amount, unit, bought)
        listDao.update(modifiedItem)
    }

    fun checkItem(product: String, bought: Boolean) {
        val item = listDao.getItem(product)
        item.Bought = bought
        listDao.update(item)
    }

    fun checkAllItems(bought: Boolean){
        listDao.checkAllItems(bought)
    }

    fun deleteCheckedItems(){
        listDao.deleteCheckedItems()
    }

    fun itemsChecked() : Boolean{
        return listDao.itemsChecked() > 0
    }
}