package com.example.foodtracking.Databases.ShoppingList

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "list_table")
class ListItem : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var Product : String = ""
    var Amount : Float = 0f
    var Bought : Boolean = false


    constructor()
    constructor(Product: String, Amount: Float, Bought: Boolean) {
        this.Product = Product
        this.Amount = Amount
        this.Bought = Bought
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
