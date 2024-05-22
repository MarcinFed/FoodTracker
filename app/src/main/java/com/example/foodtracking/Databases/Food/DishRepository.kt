package com.example.foodtracking.Databases.Food

import com.example.foodtracking.R

object DishRepository {
    private val dishes = listOf(
        Dish(
            id = 0,
            name = "Pizza Margherita",
            description = "Classic Italian pizza recipe with homemade dough and fresh ingredients.",
            category = listOf("Italian", "Pizza"),
            imageRes = R.drawable.pizza,
            youtubeVideoId = "1-SJGQ2HLp8",
            recipeSections = mapOf(
                "Dough Preparation" to "1. Combine the flour, yeast, and salt in a large bowl.\n2. Add water and stir until a sticky dough forms.\n3. Knead the dough on a floured surface for about 5 minutes until smooth.\n4. Place the dough in a greased bowl, cover, and let rise for 2 hours.",
                "Sauce Preparation" to "1. Blend the tomatoes until smooth.\n2. In a saucepan, heat the tomato puree over medium heat until it thickens.\n3. Season with salt and set aside.",
                "Pizza Preparation" to "1. Preheat your oven to the highest temperature.\n2. Roll out the dough on a floured surface.\n3. Spread the sauce over the dough, leaving a border for the crust.\n4. Sprinkle mozzarella over the sauce.\n5. Bake for about 10-15 minutes until the crust is golden and the cheese is bubbly and slightly browned."
            ),
            ingredients = listOf(
                Ingredient(name = "Flour", amount = 2f, unit = "kg"),
                Ingredient(name = "Yeast", amount = 1f, unit = "g"),
                Ingredient(name = "Salt", amount = 1f, unit = "g"),
                Ingredient(name = "Water", amount = 250f, unit = "ml"),
                Ingredient(name = "Tomato", amount = 2f, unit = ""),
                Ingredient(name = "Mozzarella", amount = 200f, unit = "g")
            )
        )
        // Add more dishes here
    )

    fun getDishes(category: String): List<Dish> {
        if (category == "All") return dishes
        return dishes.filter {
            it.category.contains(category)
        }
    }

    fun getDish(id: Int?): Dish = dishes.first { it.id == id }
}
