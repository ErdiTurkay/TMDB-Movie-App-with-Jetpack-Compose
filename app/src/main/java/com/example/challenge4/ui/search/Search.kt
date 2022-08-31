package com.example.challenge4.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.challenge4.ui.SharedViewModel
import com.example.challenge4.ui.common.MovieItem
import com.example.challenge4.ui.theme.movieName

@Composable
fun Search(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
    query: String,
    sharedViewModel: SharedViewModel
) {
    viewModel.getSearchMovies(query)
    val searchMovies by viewModel.searchMovieList

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (searchMovies.isNotEmpty()) {
            Text("Search for: $query", Modifier.padding(vertical = 10.dp))
        }

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (searchMovies.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        top = 0.dp,
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp
                    )
                ) {
                    items(searchMovies) { movie ->
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
                Text(
                    text = "No result.",
                    style = MaterialTheme.typography.movieName
                )
            }
        }
    }
}
