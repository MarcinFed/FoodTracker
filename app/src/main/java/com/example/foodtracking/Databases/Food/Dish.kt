package com.example.foodtracking.Databases.Food

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable


@Immutable
data class Ingredient(
    val name: String,
    val amount: Float,
    val unit: String
)

@Immutable
data class Dish(
    val id: Int,
    val name: String,
    val description: String,
    val category: List<String>,
    @DrawableRes val imageRes: Int,
    val youtubeUrl: String,
    val recipeSections: Map<String, String>,
    val ingredients: List<Ingredient>
)