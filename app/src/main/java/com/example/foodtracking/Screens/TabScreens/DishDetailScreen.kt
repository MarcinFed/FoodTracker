package com.example.foodtracking.Screens.TabScreens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.foodtracking.Databases.Food.Dish
import com.example.foodtracking.Databases.Food.DishRepository
import com.example.foodtracking.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishDetailScreen(dishId: Int?, navController: NavController) {
    val dish = DishRepository.getDish(dishId)
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                        Image(
                            painter = rememberImagePainter(dish.imageRes),
                            contentDescription = "Dish Image",
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.FillWidth,
                        )
                },
                modifier = Modifier
                    .fillMaxWidth(),
            )
        },
        modifier = Modifier.padding(horizontal = 27.dp)
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .verticalScroll(rememberScrollState())) {
            Text(dish.description, color = Color.Black)
            Spacer(modifier = Modifier.size(16.dp))


            Text("Ingredients", style = MaterialTheme.typography.titleLarge, color = Color.Black, fontWeight = FontWeight(500))
            dish.ingredients.forEach { ingredient -> Text("${ingredient.name}: ${ingredient.amount} ${ingredient.unit}", color = Color.Black) }


            dish.recipeSections.forEach { (section, steps) ->

                Spacer(modifier = Modifier.size(16.dp))
                Text(section, style = MaterialTheme.typography.titleLarge, color = Color.Black, fontWeight = FontWeight(500))
                Text(steps, color = Color.Black)

            }
            YoutubePlayer(dish.youtubeVideoId, LocalLifecycleOwner.current)

            Button(
                onClick = {
                    Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show() },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        color = colorResource(id = R.color.blue),
                        width = 1.dp,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Text("Add to shopping list", color = Color.Black, style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun YoutubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner
){
    AndroidView(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        factory = {context ->
        YouTubePlayerView(context = context).apply {
            lifecycleOwner.lifecycle.addObserver(this)
            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(youtubeVideoId, 0f)
                }
            })
        }
    })
}