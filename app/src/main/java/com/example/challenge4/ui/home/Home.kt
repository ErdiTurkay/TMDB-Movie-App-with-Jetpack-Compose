package com.example.challenge4.ui.home

import androidx.compose.foundation.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.challenge4.model.Movie
import com.example.challenge4.ui.SharedViewModel

@Composable
fun Home(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {
    val popularMovies by viewModel.popularMovieList
    val topRatedMovies by viewModel.topRatedMovieList
    val upcomingMovies by viewModel.upcomingMovieList

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        HomeCard(
            title = "POPULAR MOVIES",
            navigateTo = "popular",
            navController = navController,
            movies = popularMovies,
            viewModel = viewModel,
            sharedViewModel = sharedViewModel
        )
        HomeCard(
            title = "TOP RATED MOVIES",
            navigateTo = "topRated",
            navController = navController,
            movies = topRatedMovies,
            viewModel = viewModel,
            sharedViewModel = sharedViewModel
        )
        HomeCard(
            title = "UPCOMING MOVIES",
            navigateTo = "upcoming",
            navController = navController,
            movies = upcomingMovies,
            viewModel = viewModel,
            sharedViewModel = sharedViewModel
        )
    }
}