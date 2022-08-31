package com.example.challenge4.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.challenge4.Constant
import com.example.challenge4.R
import com.example.challenge4.model.Movie
import com.example.challenge4.ui.SharedViewModel
import com.example.challenge4.ui.theme.* // ktlint-disable no-wildcard-imports

@Composable
fun MovieItem(
    movie: Movie,
    index: Int = -1,
    navController: NavController,
    sharedViewModel: SharedViewModel,
    method: () -> Unit
) {
    val painter = rememberImagePainter(
        data = Constant.IMAGE_PATH_PREFIX + movie.posterPath,
        builder = {
            placeholder(R.drawable.placeholder_movie)
            error(R.drawable.placeholder_movie)
            crossfade(Constant.CROSSFADE_DURATION)
            /*transformations(
                // BlurTransformation(LocalContext.current),
                // RoundedCornersTransformation(50f)
            )*/
        }
    )

    var favorite by remember { mutableStateOf(movie.isFavorite) }

    val color = chooseFavoriteColor(favorite)
    val rateBackground = chooseRateColor(movie.voteAverage)

    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                sharedViewModel.addMovie(movie)
                navController.navigate("movieDetail") {
                    navController.previousBackStackEntry?.let {
                        popUpTo(it.id) {
                            saveState = true
                            navController.currentBackStackEntry?.savedStateHandle?.clearSavedStateProvider("movie")
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
    ) {
        Column {
            Image(
                contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = "Logo Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .height(3.dp)
                    .fillMaxWidth()
            ) {}
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(47.dp)
            ) {
                Text(
                    text = if (index != -1) {
                        "#${index + 1} ${movie.title}"
                    } else {
                        movie.title
                    },
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.movieName,
                    maxLines = 2
                )
            }
        }
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier.padding(
                end = 5.dp,
                top = 3.dp
            )
        ) {
            FavoriteBar(
                favoriteStatus = favorite,
                color = color,
                method = {
                    method()
                    favorite = !favorite
                }
            )
        }
        Box(
            contentAlignment = Alignment.TopStart
        ) {
            RateBar(
                rateBackground = rateBackground,
                voteAverage = movie.voteAverage
            )
        }
    }
}
