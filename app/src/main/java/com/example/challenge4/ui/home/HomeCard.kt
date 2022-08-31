package com.example.challenge4.ui.home

import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.challenge4.model.Movie
import com.example.challenge4.ui.MainViewModel
import com.example.challenge4.ui.SharedViewModel
import com.example.challenge4.ui.theme.Shapes
import com.example.challenge4.ui.theme.homeTitle

@Composable
fun HomeCard(
    title: String,
    navigateTo: String,
    navController: NavController,
    movies: List<Movie>,
    viewModel: HomeViewModel,
    sharedViewModel: SharedViewModel
) {
    Card(
        shape = Shapes.small,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 5.dp,
                    start = 6.dp,
                    end = 6.dp,
                    bottom = 12.dp
                )
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.homeTitle,
                    modifier = Modifier.padding(start = 8.dp)
                )
                OutlinedButton(
                    onClick = {
                        navController.navigate(navigateTo) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                ) {
                    Text(text = "VIEW ALL")
                }
            }

            Conservation(
                movieList = movies,
                navController = navController,
                viewModel = viewModel,
                sharedViewModel = sharedViewModel
            )
        }
    }
}

@Composable
fun Conservation(
    movieList: List<Movie>,
    navController: NavController,
    viewModel: HomeViewModel,
    sharedViewModel: SharedViewModel
) {
    LazyRow(
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(items = movieList) { movie ->
            HomeMovieItem(
                movie = movie,
                navController = navController,
                viewModel = viewModel,
                sharedViewModel = sharedViewModel
            )
        }
    }
}

