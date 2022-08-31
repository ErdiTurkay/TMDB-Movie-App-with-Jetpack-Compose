package com.example.challenge4.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.challenge4.ui.SharedViewModel
import com.example.challenge4.ui.common.ActorItem
import com.example.challenge4.ui.common.MovieItem
import com.example.challenge4.ui.theme.movieName

@Composable
fun Favorite(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        MyTab(
            navController = navController,
            viewModel = viewModel,
            sharedViewModel = sharedViewModel
        )
    }
}

@Composable
fun MyTab(
    viewModel: FavoriteViewModel,
    sharedViewModel: SharedViewModel,
    navController: NavController
) {
    viewModel.initialize()

    var state by remember { mutableStateOf(0) }
    val titles = listOf("MOVIES", "ACTORS")

    val favoriteMovies by viewModel.favoriteMovies
    val favoriteActors by viewModel.favoriteActors

    Column {
        TabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = state == index,
                    onClick = { state = index }
                )
            }
        }

        if (state == titles.indexOf("MOVIES")) {
            if (favoriteMovies.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(favoriteMovies) { movie ->
                        MovieItem(
                            movie = movie,
                            navController = navController,
                            method = {
                                viewModel.changeFavorite(movie)
                            },
                            sharedViewModel = sharedViewModel
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No favorite added yet.",
                        style = MaterialTheme.typography.movieName
                    )
                }
            }
        } else if (state == titles.indexOf("ACTORS")) {
            if (favoriteActors.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(favoriteActors) { actor ->
                        ActorItem(
                            actor = actor,
                            navController = navController,
                            method = {}
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No favorite added yet.",
                        style = MaterialTheme.typography.movieName
                    )
                }
            }
        }
    }
}
