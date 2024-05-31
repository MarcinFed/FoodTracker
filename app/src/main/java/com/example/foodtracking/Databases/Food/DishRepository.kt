package com.example.foodtracking.Databases.Food

import com.example.foodtracking.R

object DishRepository {
    private val dishes = listOf(
        Dish(
            id = 0,
            name = "Pizza Margherita",
            description = "Classic Italian pizza recipe with homemade dough and fresh ingredients.",
            category = listOf("Italian", "Pizza"),
            imageRes = R.drawable.pizza_margherita,
            youtubeVideoId = "1-SJGQ2HLp8",
            recipeSections = mapOf(
                "Dough Preparation" to "1. Combine the flour, yeast, and salt in a large bowl.\n2. Add water and stir until a sticky dough forms.\n3. Knead the dough on a floured surface for about 5 minutes until smooth.\n4. Place the dough in a greased bowl, cover, and let rise for 2 hours.",
                "Sauce Preparation" to "1. Blend the tomatoes until smooth.\n2. In a saucepan, heat the tomato puree over medium heat until it thickens.\n3. Season with salt and set aside.",
                "Pizza Preparation" to "1. Preheat your oven to the highest temperature.\n2. Roll out the dough on a floured surface.\n3. Spread the sauce over the dough, leaving a border for the crust.\n4. Sprinkle mozzarella over the sauce.\n5. Bake for about 10-15 minutes until the crust is golden and the cheese is bubbly and slightly browned."
            ),
            ingredients = listOf(
                Ingredient(name = "Flour", amount = 500f, unit = "g"),
                Ingredient(name = "Yeast", amount = 7f, unit = "g"),
                Ingredient(name = "Salt", amount = 10f, unit = "g"),
                Ingredient(name = "Water", amount = 250f, unit = "ml"),
                Ingredient(name = "Tomato", amount = 2f, unit = ""),
                Ingredient(name = "Mozzarella", amount = 200f, unit = "g")
            )
        ),
        Dish(
            id = 1,
            name = "Pepperoni Pizza",
            description = "Spicy pepperoni pizza with a crispy crust and lots of cheese.",
            category = listOf("Italian", "Pizza"),
            imageRes = R.drawable.pepperoni_pizza,
            youtubeVideoId = "HCAPjIVOdJw",
            recipeSections = mapOf(
                "Dough Preparation" to "1. Mix the flour, yeast, and salt in a bowl.\n2. Add warm water and mix until a dough forms.\n3. Knead the dough for 5-7 minutes until smooth.\n4. Let the dough rise in a warm place for 1 hour.",
                "Sauce Preparation" to "1. Puree the tomatoes in a blender.\n2. Simmer the tomato puree in a saucepan over medium heat until thickened.\n3. Add salt and oregano to taste.",
                "Pizza Preparation" to "1. Preheat the oven to 250°C (482°F).\n2. Roll out the dough on a floured surface.\n3. Spread the sauce over the dough.\n4. Add mozzarella cheese and pepperoni slices.\n5. Bake for 10-15 minutes until the crust is golden and the cheese is melted."
            ),
            ingredients = listOf(
                Ingredient(name = "Flour", amount = 500f, unit = "g"),
                Ingredient(name = "Yeast", amount = 7f, unit = "g"),
                Ingredient(name = "Salt", amount = 10f, unit = "g"),
                Ingredient(name = "Water", amount = 300f, unit = "ml"),
                Ingredient(name = "Tomatoes", amount = 400f, unit = "g"),
                Ingredient(name = "Mozzarella", amount = 250f, unit = "g"),
                Ingredient(name = "Pepperoni", amount = 100f, unit = "g")
            )
        ),

        Dish(
            id = 2,
            name = "California Roll",
            description = "Popular sushi roll with crab, avocado, and cucumber.",
            category = listOf("Japanese", "Sushi"),
            imageRes = R.drawable.california_roll,
            youtubeVideoId = "84FqztDzWjE",
            recipeSections = mapOf(
                "Rice Preparation" to "1. Rinse the sushi rice until the water runs clear.\n2. Cook the rice according to the package instructions.\n3. Mix the cooked rice with rice vinegar, sugar, and salt. Let it cool.",
                "Roll Preparation" to "1. Lay a sheet of nori on a bamboo mat.\n2. Spread a thin layer of rice on the nori.\n3. Place crab meat, avocado slices, and cucumber strips on the rice.\n4. Roll the sushi tightly using the bamboo mat.\n5. Cut into bite-sized pieces."
            ),
            ingredients = listOf(
                Ingredient(name = "Sushi rice", amount = 300f, unit = "g"),
                Ingredient(name = "Rice vinegar", amount = 30f, unit = "ml"),
                Ingredient(name = "Sugar", amount = 10f, unit = "g"),
                Ingredient(name = "Salt", amount = 5f, unit = "g"),
                Ingredient(name = "Nori", amount = 4f, unit = ""),
                Ingredient(name = "Crab meat", amount = 200f, unit = "g"),
                Ingredient(name = "Avocado", amount = 1f, unit = ""),
                Ingredient(name = "Cucumber", amount = 1f, unit = "")
            )
        ),
        Dish(
            id = 3,
            name = "Spicy Tuna Roll",
            description = "Sushi roll with spicy tuna, cucumber, and avocado.",
            category = listOf("Japanese", "Sushi"),
            imageRes = R.drawable.spicy_tuna_roll,
            youtubeVideoId = "WeizJSIHSnQ",
            recipeSections = mapOf(
                "Rice Preparation" to "1. Rinse the sushi rice until the water is clear.\n2. Cook the rice and mix with rice vinegar, sugar, and salt. Let it cool.",
                "Tuna Preparation" to "1. Chop the tuna into small pieces.\n2. Mix the tuna with spicy mayo.",
                "Roll Preparation" to "1. Place a nori sheet on a bamboo mat.\n2. Spread a layer of rice on the nori.\n3. Add the spicy tuna, cucumber strips, and avocado slices.\n4. Roll the sushi and cut into pieces."
            ),
            ingredients = listOf(
                Ingredient(name = "Sushi rice", amount = 300f, unit = "g"),
                Ingredient(name = "Rice vinegar", amount = 30f, unit = "ml"),
                Ingredient(name = "Sugar", amount = 10f, unit = "g"),
                Ingredient(name = "Salt", amount = 5f, unit = "g"),
                Ingredient(name = "Nori", amount = 4f, unit = ""),
                Ingredient(name = "Tuna", amount = 200f, unit = "g"),
                Ingredient(name = "Spicy mayo", amount = 30f, unit = "g"),
                Ingredient(name = "Cucumber", amount = 1f, unit = ""),
                Ingredient(name = "Avocado", amount = 1f, unit = "")
            )
        ),
        Dish(
            id = 4,
            name = "Classic Cheeseburger",
            description = "Juicy beef patty with cheddar cheese, lettuce, tomato, and pickles.",
            category = listOf("American", "Burger"),
            imageRes = R.drawable.classic_cheeseburger,
            youtubeVideoId = "foD42-73wdI",
            recipeSections = mapOf(
                "Patty Preparation" to "1. Mix ground beef with salt and pepper.\n2. Form the mixture into patties.\n3. Grill the patties for 4-5 minutes on each side until fully cooked.",
                "Assembly" to "1. Toast the burger buns.\n2. Place the lettuce, tomato, and pickles on the bottom bun.\n3. Add the grilled patty and top with cheddar cheese.\n4. Cover with the top bun and serve."
            ),
            ingredients = listOf(
                Ingredient(name = "Ground beef", amount = 500f, unit = "g"),
                Ingredient(name = "Salt", amount = 5f, unit = "g"),
                Ingredient(name = "Black pepper", amount = 5f, unit = "g"),
                Ingredient(name = "Burger buns", amount = 4f, unit = ""),
                Ingredient(name = "Lettuce", amount = 100f, unit = "g"),
                Ingredient(name = "Tomato", amount = 1f, unit = ""),
                Ingredient(name = "Pickles", amount = 60f, unit = "g"),
                Ingredient(name = "Cheddar cheese", amount = 1f, unit = "")
            )
        ),
        Dish(
            id = 5,
            name = "Bacon BBQ Burger",
            description = "Beef patty with crispy bacon, BBQ sauce, and onion rings.",
            category = listOf("American", "Burger"),
            imageRes = R.drawable.bacon_bbq_burger,
            youtubeVideoId = "BwlST30svmc",
            recipeSections = mapOf(
                "Patty Preparation" to "1. Mix ground beef with salt and pepper.\n2. Shape into patties and grill for 4-5 minutes on each side.",
                "Assembly" to "1. Toast the burger buns.\n2. Spread BBQ sauce on the bottom bun.\n3. Add lettuce, tomato, grilled patty, crispy bacon, and onion rings.\n4. Top with more BBQ sauce and the top bun."
            ),
            ingredients = listOf(
                Ingredient(name = "Ground beef", amount = 500f, unit = "g"),
                Ingredient(name = "Salt", amount = 5f, unit = "g"),
                Ingredient(name = "Black pepper", amount = 5f, unit = "g"),
                Ingredient(name = "Burger buns", amount = 4f, unit = ""),
                Ingredient(name = "Lettuce", amount = 100f, unit = "g"),
                Ingredient(name = "Tomato", amount = 1f, unit = ""),
                Ingredient(name = "BBQ sauce", amount = 60f, unit = "ml"),
                Ingredient(name = "Bacon", amount = 100f, unit = "g"),
                Ingredient(name = "Onion rings", amount = 4f, unit = "")
            )
        ),
        Dish(
            id = 6,
            name = "Spaghetti Carbonara",
            description = "Classic Italian pasta with a creamy sauce made from eggs, cheese, pancetta, and pepper.",
            category = listOf("Italian", "Pasta"),
            imageRes = R.drawable.spaghetti_carbonara,
            youtubeVideoId = "3AAdKl1UYZs",
            recipeSections = mapOf(
                "Pasta Preparation" to "1. Cook the spaghetti in a large pot of salted boiling water until al dente.\n2. Drain the pasta, reserving some of the pasta water.",
                "Sauce Preparation" to "1. Cook the pancetta in a large skillet until crispy.\n2. In a bowl, whisk together the eggs and grated cheese.\n3. Add the hot pasta to the skillet with the pancetta and toss to coat.\n4. Remove from heat and quickly stir in the egg mixture, adding reserved pasta water as needed to create a creamy sauce.\n5. Season with black pepper and serve immediately."
            ),
            ingredients = listOf(
                Ingredient(name = "Spaghetti", amount = 400f, unit = "g"),
                Ingredient(name = "Pancetta", amount = 150f, unit = "g"),
                Ingredient(name = "Eggs", amount = 4f, unit = ""),
                Ingredient(name = "Parmesan cheese", amount = 100f, unit = "g"),
                Ingredient(name = "Black pepper", amount = 5f, unit = "g")
            )
        ),
        Dish(
            id = 7,
            name = "Penne Arrabbiata",
            description = "Spicy Italian pasta dish with a tangy tomato sauce and chili flakes.",
            category = listOf("Italian", "Pasta"),
            imageRes = R.drawable.penne_arrabbiata,
            youtubeVideoId = "48HMaU_4T3M",
            recipeSections = mapOf(
                "Pasta Preparation" to "1. Cook the penne in a large pot of salted boiling water until al dente.\n2. Drain the pasta.",
                "Sauce Preparation" to "1. Heat olive oil in a large skillet over medium heat.\n2. Add minced garlic and chili flakes, and sauté until fragrant.\n3. Add canned tomatoes and simmer until the sauce thickens.\n4. Season with salt and pepper.\n5. Toss the cooked penne with the sauce and serve."
            ),
            ingredients = listOf(
                Ingredient(name = "Penne", amount = 400f, unit = "g"),
                Ingredient(name = "Olive oil", amount = 30f, unit = "ml"),
                Ingredient(name = "Garlic", amount = 15f, unit = "g"),
                Ingredient(name = "Chili flakes", amount = 5f, unit = "g"),
                Ingredient(name = "Canned tomatoes", amount = 800f, unit = "g"),
                Ingredient(name = "Salt", amount = 5f, unit = "g"),
                Ingredient(name = "Black pepper", amount = 5f, unit = "g")
            )
        ),
        Dish(
            id = 8,
            name = "Pork Dumplings",
            description = "Delicious steamed dumplings filled with seasoned pork.",
            category = listOf("Asian", "Dumplings"),
            imageRes = R.drawable.pork_dumplings,
            youtubeVideoId = "qWkvFssMz24",
            recipeSections = mapOf(
                "Dough Preparation" to "1. Mix flour and water to form a dough.\n2. Knead the dough until smooth, then cover and let it rest for 30 minutes.",
                "Filling Preparation" to "1. Combine ground pork, soy sauce, sesame oil, ginger, and garlic in a bowl.\n2. Mix well until all ingredients are evenly distributed.",
                "Dumpling Preparation" to "1. Roll the dough into a thin sheet and cut into circles.\n2. Place a spoonful of filling in the center of each circle.\n3. Fold the dough over the filling and pinch the edges to seal.\n4. Steam the dumplings for 10-12 minutes until cooked through."
            ),
            ingredients = listOf(
                Ingredient(name = "Flour", amount = 300f, unit = "g"),
                Ingredient(name = "Water", amount = 150f, unit = "ml"),
                Ingredient(name = "Ground pork", amount = 200f, unit = "g"),
                Ingredient(name = "Soy sauce", amount = 30f, unit = "ml"),
                Ingredient(name = "Sesame oil", amount = 15f, unit = "ml"),
                Ingredient(name = "Ginger", amount = 15f, unit = "g"),
                Ingredient(name = "Garlic", amount = 10f, unit = "g")
            )
        ),
        Dish(
            id = 9,
            name = "Vegetable Dumplings",
            description = "Healthy and delicious dumplings filled with mixed vegetables.",
            category = listOf("Asian", "Dumplings"),
            imageRes = R.drawable.vegetable_dumplings,
            youtubeVideoId = "YJmuRCWd1IY",
            recipeSections = mapOf(
                "Dough Preparation" to "1. Combine flour and water to form a dough.\n2. Knead the dough until smooth and let it rest for 30 minutes.",
                "Filling Preparation" to "1. Chop the vegetables finely.\n2. Mix the vegetables with soy sauce, sesame oil, and salt.",
                "Dumpling Preparation" to "1. Roll out the dough and cut into circles.\n2. Place a spoonful of vegetable filling in each circle.\n3. Fold the dough and seal the edges.\n4. Steam the dumplings for 10-12 minutes."
            ),
            ingredients = listOf(
                Ingredient(name = "Flour", amount = 300f, unit = "g"),
                Ingredient(name = "Water", amount = 150f, unit = "ml"),
                Ingredient(name = "Mixed vegetables", amount = 200f, unit = "g"),
                Ingredient(name = "Soy sauce", amount = 30f, unit = "ml"),
                Ingredient(name = "Sesame oil", amount = 15f, unit = "ml"),
                Ingredient(name = "Salt", amount = 5f, unit = "g")
            )
        ),
        Dish(
            id = 10,
            name = "Chocolate Cake",
            description = "A delicious chocolate cake.",
            category = listOf("Sweet", "Dessert"),
            imageRes = R.drawable.chocolate_cake,
            youtubeVideoId = "vI5w-fK25w4",
            recipeSections = mapOf(
                "Cake Preparation" to "1. Preheat oven to 180°C.\n2. Mix flour, sugar, cocoa powder, baking powder, and salt.\n3. Add eggs, milk, oil, and vanilla extract. Mix well.\n4. Pour batter into a greased baking pan.\n5. Bake for 30-35 minutes.",
                "Frosting Preparation" to "1. Melt chocolate and butter.\n2. Add powdered sugar and milk, mix until smooth.\n3. Spread frosting over the cooled cake."
            ),
            ingredients = listOf(
                Ingredient(name = "Flour", amount = 200f, unit = "g"),
                Ingredient(name = "Sugar", amount = 150f, unit = "g"),
                Ingredient(name = "Cocoa powder", amount = 50f, unit = "g"),
                Ingredient(name = "Baking powder", amount = 10f, unit = "g"),
                Ingredient(name = "Salt", amount = 2f, unit = "g"),
                Ingredient(name = "Eggs", amount = 2f, unit = ""),
                Ingredient(name = "Milk", amount = 200f, unit = "ml"),
                Ingredient(name = "Oil", amount = 100f, unit = "ml"),
                Ingredient(name = "Vanilla extract", amount = 5f, unit = "ml"),
                Ingredient(name = "Chocolate", amount = 100f, unit = "g"),
                Ingredient(name = "Butter", amount = 50f, unit = "g"),
                Ingredient(name = "Powdered sugar", amount = 100f, unit = "g")
            )
        ),
        Dish(
            id = 11,
            name = "Apple Pie",
            description = "A classic apple pie.",
            category = listOf("Sweet", "Dessert"),
            imageRes = R.drawable.apple_pie,
            youtubeVideoId = "PzFo8G6YNz0",
            recipeSections = mapOf(
                "Pie Crust" to "1. Mix flour and salt.\n2. Cut in butter until the mixture resembles coarse crumbs.\n3. Add water and mix until dough forms.\n4. Roll out dough and fit into a pie plate.",
                "Apple Filling" to "1. Peel, core, and slice apples.\n2. Mix apples with sugar, cinnamon, and lemon juice.\n3. Pour apple mixture into pie crust.\n4. Cover with top crust and seal edges.\n5. Bake at 200°C for 45-50 minutes."
            ),
            ingredients = listOf(
                Ingredient(name = "Flour", amount = 300f, unit = "g"),
                Ingredient(name = "Salt", amount = 5f, unit = "g"),
                Ingredient(name = "Butter", amount = 150f, unit = "g"),
                Ingredient(name = "Water", amount = 100f, unit = "ml"),
                Ingredient(name = "Apples", amount = 6f, unit = ""),
                Ingredient(name = "Sugar", amount = 100f, unit = "g"),
                Ingredient(name = "Cinnamon", amount = 5f, unit = "g"),
                Ingredient(name = "Lemon juice", amount = 10f, unit = "ml")
            )
        ),
        Dish(
            id = 16,
            name = "Nutella and Strawberry Pancakes",
            description = "Delicious pancakes topped with Nutella and fresh strawberries.",
            category = listOf("Sweet", "Breakfast"),
            imageRes = R.drawable.nutella_strawberry_pancakes,
            youtubeVideoId = "vmQmQCh-8uQ",
            recipeSections = mapOf(
                "Pancake Batter Preparation" to "1. In a bowl, mix the flour, sugar, baking powder, and salt.\n2. In another bowl, whisk together the milk, egg, and melted butter.\n3. Pour the wet ingredients into the dry ingredients and mix until just combined. Do not overmix.",
                "Cooking the Pancakes" to "1. Heat a non-stick pan over medium heat and lightly grease it with butter or oil.\n2. Pour a small amount of batter onto the pan and cook until bubbles form on the surface.\n3. Flip the pancake and cook until golden brown on the other side.\n4. Repeat with the remaining batter.",
                "Serving" to "1. Spread a generous amount of Nutella over each pancake.\n2. Top with sliced strawberries.\n3. Stack the pancakes and serve immediately."
            ),
            ingredients = listOf(
                Ingredient(name = "Flour", amount = 200f, unit = "g"),
                Ingredient(name = "Sugar", amount = 50f, unit = "g"),
                Ingredient(name = "Baking powder", amount = 10f, unit = "g"),
                Ingredient(name = "Salt", amount = 2f, unit = "g"),
                Ingredient(name = "Milk", amount = 250f, unit = "ml"),
                Ingredient(name = "Egg", amount = 1f, unit = ""),
                Ingredient(name = "Butter", amount = 50f, unit = "g"),
                Ingredient(name = "Nutella", amount = 100f, unit = "g"),
                Ingredient(name = "Strawberries", amount = 200f, unit = "g")
            )
        ),
        Dish(
            id = 12,
            name = "Caesar Salad",
            description = "A fresh Caesar salad.",
            category = listOf("Salad"),
            imageRes = R.drawable.caesar_salad,
            youtubeVideoId = "a4Z2x0sPq3A",
            recipeSections = mapOf(
                "Dressing Preparation" to "1. Mix lemon juice, Dijon mustard, and garlic.\n2. Add mayonnaise and Parmesan cheese.\n3. Mix until smooth.",
                "Salad Assembly" to "1. Chop lettuce and place in a bowl.\n2. Add croutons and Parmesan cheese.\n3. Drizzle with dressing and toss to coat."
            ),
            ingredients = listOf(
                Ingredient(name = "Lemon juice", amount = 30f, unit = "ml"),
                Ingredient(name = "Dijon mustard", amount = 10f, unit = "g"),
                Ingredient(name = "Garlic", amount = 2f, unit = ""),
                Ingredient(name = "Mayonnaise", amount = 100f, unit = "g"),
                Ingredient(name = "Parmesan cheese", amount = 50f, unit = "g"),
                Ingredient(name = "Lettuce", amount = 1f, unit = ""),
                Ingredient(name = "Croutons", amount = 50f, unit = "g")
            )
        ),
        Dish(
            id = 13,
            name = "Greek Salad",
            description = "A tasty Greek salad.",
            category = listOf("Salad"),
            imageRes = R.drawable.greek_salad,
            youtubeVideoId = "dDhOpHcAJGo",
            recipeSections = mapOf(
                "Salad Preparation" to "1. Chop tomatoes, cucumber, and onion.\n2. Add olives and feta cheese.\n3. Drizzle with olive oil and vinegar.\n4. Toss to combine."
            ),
            ingredients = listOf(
                Ingredient(name = "Tomatoes", amount = 4f, unit = ""),
                Ingredient(name = "Cucumber", amount = 1f, unit = ""),
                Ingredient(name = "Onion", amount = 1f, unit = ""),
                Ingredient(name = "Olives", amount = 100f, unit = "g"),
                Ingredient(name = "Feta cheese", amount = 200f, unit = "g"),
                Ingredient(name = "Olive oil", amount = 50f, unit = "ml"),
                Ingredient(name = "Vinegar", amount = 20f, unit = "ml")
            )
        ),
        Dish(
            id = 14,
            name = "Tomato Soup",
            description = "A warm tomato soup.",
            category = listOf("Soup"),
            imageRes = R.drawable.tomato_soup,
            youtubeVideoId = "szjZ3vqwyXE",
            recipeSections = mapOf(
                "Soup Preparation" to "1. Heat oil in a pot.\n2. Add onions and garlic, cook until soft.\n3. Add tomatoes, broth, and seasonings.\n4. Simmer for 20 minutes.\n5. Blend until smooth."
            ),
            ingredients = listOf(
                Ingredient(name = "Oil", amount = 30f, unit = "ml"),
                Ingredient(name = "Onion", amount = 1f, unit = ""),
                Ingredient(name = "Garlic", amount = 3f, unit = ""),
                Ingredient(name = "Tomatoes", amount = 800f, unit = "g"),
                Ingredient(name = "Broth", amount = 500f, unit = "ml"),
                Ingredient(name = "Salt", amount = 5f, unit = "g"),
                Ingredient(name = "Pepper", amount = 2f, unit = "g")
            )
        ),
        Dish(
            id = 15,
            name = "Chicken Soup",
            description = "A comforting chicken soup.",
            category = listOf("Soup"),
            imageRes = R.drawable.chicken_soup,
            youtubeVideoId = "l_l1Yr-OcGY",
            recipeSections = mapOf(
                "Soup Preparation" to "1. Heat oil in a pot.\n2. Add chicken and cook until browned.\n3. Add vegetables and cook until soft.\n4. Add broth and bring to a boil.\n5. Simmer for 30 minutes."
            ),
            ingredients = listOf(
                Ingredient(name = "Oil", amount = 30f, unit = "ml"),
                Ingredient(name = "Chicken", amount = 500f, unit = "g"),
                Ingredient(name = "Carrot", amount = 2f, unit = ""),
                Ingredient(name = "Celery", amount = 1f, unit = ""),
                Ingredient(name = "Onion", amount = 1f, unit = ""),
                Ingredient(name = "Broth", amount = 1000f, unit = "ml"),
                Ingredient(name = "Salt", amount = 5f, unit = "g"),
                Ingredient(name = "Pepper", amount = 2f, unit = "g")
            )
        )
    ).sortedBy { it.name }
    fun getDishes(category: String): List<Dish> {
        if (category == "All") return dishes
        return dishes.filter {
            it.category.contains(category)
        }
    }

    fun getDish(id: Int?): Dish = dishes.first { it.id == id }
}
