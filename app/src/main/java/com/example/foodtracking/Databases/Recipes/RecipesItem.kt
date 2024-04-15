package com.example.foodtracking.Databases.Recipes

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodtracking.R
import java.io.Serializable

@Entity(tableName = "recipes_table")
class RecipesItem : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name = ""
    var short_description = ""
    var ingredients = ""
    var instruction = ""
    var photo: Int = R.drawable.recipes_placeholder
    var video: String = "https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley"



    constructor()
    constructor(name: String, short_description: String, ingredients: String, instruction: String, photo: Int, video: String) {
        this.name = name
        this.short_description = short_description
        this.ingredients = ingredients
        this.instruction = instruction
        this.photo = photo
        this.video = video
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
