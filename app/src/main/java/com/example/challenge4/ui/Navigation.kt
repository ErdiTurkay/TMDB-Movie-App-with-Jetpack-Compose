package com.example.challenge4.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.challenge4.R
import com.example.challenge4.ui.favorite.Favorite
import com.example.challenge4.ui.home.Home
import com.example.challenge4.ui.moviedetail.MovieDetail
import com.example.challenge4.ui.popular.Popular
import com.example.challenge4.ui.search.Search
import com.example.challenge4.ui.toprated.TopRated
import com.example.challenge4.ui.upcoming.Upcoming

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Popular : NavRoutes("popular")
    object TopRated : NavRoutes("topRated")
    object Upcoming : NavRoutes("upcoming")
    object Favorite : NavRoutes("favorite")
    object Search : NavRoutes("search")
    object MovieDetail : NavRoutes("movieDetail")
}

@Composable
fun NavigationHost(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            Home(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(NavRoutes.Popular.route) {
            Popular(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(NavRoutes.TopRated.route) {
            TopRated(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(NavRoutes.Upcoming.route) {
            Upcoming(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(NavRoutes.Favorite.route) {
            Favorite(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(
            route = NavRoutes.Search.route + "/{query}",
            content = {
                val query = it.arguments?.getString("query")

                if (query != null) {
                    Search(
                        navController = navController,
                        query = query,
                        sharedViewModel = sharedViewModel
                    )
                }
            }
        )

        composable(NavRoutes.MovieDetail.route) {
            MovieDetail(sharedViewModel = sharedViewModel)
        }
    }
}

object NavBarItems {
    val bottomBarItems = listOf(
        BarItem(
            title = "Home",
            icon = R.drawable.ic_baseline_home_24,
            route = "home"
        ),
        BarItem(
            title = "Popular",
            icon = R.drawable.ic_baseline_offline_bolt_24,
            route = "popular"
        ),
        BarItem(
            title = "Top Rated",
            icon = R.drawable.ic_baseline_star_rate_24,
            route = "topRated"
        ),
        BarItem(
            title = "Upcoming",
            icon = R.drawable.ic_baseline_access_time_filled_24,
            route = "upcoming"
        ),
        BarItem(
            title = "Favorite",
            icon = R.drawable.ic_baseline_favorite_24,
            route = "favorite"
        )
    )
}

data class BarItem(
    val title: String,
    val icon: Int,
    val route: String
)
