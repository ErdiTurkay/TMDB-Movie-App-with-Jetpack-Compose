package com.example.challenge4.ui.toprated

import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.challenge4.ui.SharedViewModel
import com.example.challenge4.ui.common.MovieItem

@Composable
fun TopRated(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: TopRatedViewModel = hiltViewModel()
) {
    val topRatedMovies by viewModel.topRatedMovieList

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(topRatedMovies) { index, movie ->
                MovieItem(
                    movie = movie,
                    index = index,
                    navController = navController,
                    method = {
                        viewModel.changeFavorite(movie)
                    },
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }
}
