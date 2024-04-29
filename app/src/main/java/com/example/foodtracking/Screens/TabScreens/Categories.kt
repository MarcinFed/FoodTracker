package com.example.foodtracking.Screens.TabScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.foodtracking.Databases.Categories.Category
import com.example.foodtracking.R
import com.example.foodtracking.ui.theme.FoodTrackingTheme

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Categories(pagerState: PagerState) {

    val categories = remember {
        listOf(
            Category("Pizza", "Italian dish with dough base, cheese, and toppings", R.drawable.pizza),
            Category("Sushi", "Vinegar-flavored rice with fish, vegetables, or egg", R.drawable.sushi),
            Category("Burger", "Meat patty in a bun, often with lettuce, tomato, and sauces", R.drawable.burger),
            Category("Pasta", "Versatile noodles served with a range of sauces and toppings from meats to vegetables", R.drawable.pasta),
            Category("Dumplings", "Small dough parcels filled with meat or vegetables, steamed, boiled, or fried", R.drawable.dumplings),
            Category("Sweer", "Delightful sweet treats from fluffy pancakes to rich waffles, often topped with syrup, fruits, and whipped cream", R.drawable.sweet),
            Category("Pizza", "Italian dish with dough base, cheese, and toppings", R.drawable.pizza),
            Category("Sushi", "Vinegar-flavored rice with fish, vegetables, or egg", R.drawable.sushi),
            Category("Burger", "Meat patty in a bun, often with lettuce, tomato, and sauces", R.drawable.burger),
            Category("Pasta", "Versatile noodles served with a range of sauces and toppings from meats to vegetables", R.drawable.pasta),
            Category("Dumplings", "Small dough parcels filled with meat or vegetables, steamed, boiled, or fried", R.drawable.dumplings),
            Category("Sweer", "Delightful sweet treats from fluffy pancakes to rich waffles, often topped with syrup, fruits, and whipped cream", R.drawable.sweet),
        )
    }

    Scaffold {
        FoodTrackingTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 15.dp, start = 15.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                CategoryList(categories)
            }
        }
    }
}


@Composable
fun CategoryList(categories: List<Category>) {
    LazyColumn {
        items(categories) { category ->
            CategoryListItem(category)
        }
    }
}

@Composable
fun CategoryListItem(category: Category) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(8.dp),
        elevation =  CardDefaults.cardElevation(defaultElevation = 7.dp, pressedElevation = 7.dp),
        onClick = { /* Insert the onClick action here */},
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = category.imageRes,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = "${category.name} image",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .height(100.dp)
                    .width(100.dp),
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier
                    .padding(end = 8.dp, start = 8.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = category.description,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun CaegoryListItemPreview() {
    CategoryListItem(
        Category("Pizza", "Italian dish with dough base, cheese, and toppings", R.drawable.pizza)
    )
}