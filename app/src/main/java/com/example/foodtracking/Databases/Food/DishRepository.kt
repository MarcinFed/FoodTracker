package com.example.foodtracking.Databases.Food

import com.example.foodtracking.R

object DishRepository {
    private val dishes = listOf(
        Dish(
            id = 1,
            name = "Pizza",
            description = "Classic Italian pizza recipe with homemade dough.",
            category = listOf("Italian", "Pizza"),
            imageRes = R.drawable.pizza,
            youtubeUrl = "https://www.youtube.com/watch?v=1-SJGQ2HLp8&ab_channel=JamieOliver",
            recipeSections = mapOf(
                "Preparation" to "Mix ingredients for dough.",
                "Baking" to "Bake at 220°C for 20 minutes."
            ),
            ingredients = listOf(
                Ingredient(name = "Flour", amount = 2f, unit = "cups"),
                Ingredient(name = "Yeast", amount = 1f, unit = "tsp")
            )
        )
        // Add more dishes here
    )

    fun getDishes(): List<Dish> = dishes
    fun getDish(id: Int?): Dish = dishes.first { it.id == id }
}
