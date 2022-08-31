package com.example.challenge4.ui.upcoming

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
fun Upcoming(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: UpcomingViewModel = hiltViewModel()
) {
    val topRatedMovies by viewModel.upcomingMovieList

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
                    sharedViewModel = sharedViewModel,
                    method = {
                        viewModel.changeFavorite(movie)
                    }
                )
            }
        }
    }
}
