package com.example.foodtracking.Databases.ShoppingList

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "list_table")
class ListItem : Serializable {
    @PrimaryKey(autoGenerate = false)
    var Product : String = ""
    var Amount : Float = 0f
    var Unit : String = ""
    var Bought : Boolean = false


    constructor()
    constructor(Product: String, Amount: Float, Unit: String, Bought: Boolean) {
        this.Product = Product
        this.Amount = Amount
        this.Unit = Unit
        this.Bought = Bought
    }

    override fun equals(other: Any?): Boolean {
        return if (other is ListItem) {
            this.Product == other.Product && this.Amount == other.Amount && this.Unit == other.Unit && this.Bought == other.Bought
        } else {
            false
        }
    }
}
