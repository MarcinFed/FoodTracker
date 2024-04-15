package com.example.foodtracking.Databases.Categories

import androidx.annotation.DrawableRes

data class Category(
    val name: String,
    val description: String,
    @DrawableRes val imageRes: Int
)

