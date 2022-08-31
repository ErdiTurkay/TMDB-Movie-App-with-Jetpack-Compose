package com.example.challenge4.ui.popular

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
fun Popular(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: PopularViewModel = hiltViewModel()
) {
    val popularMovies by viewModel.popularMovieList

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(popularMovies) { index, movie ->
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
