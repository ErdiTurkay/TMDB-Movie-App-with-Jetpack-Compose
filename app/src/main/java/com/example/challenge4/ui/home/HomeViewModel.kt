package com.example.challenge4.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge4.MovieAPIService
import com.example.challenge4.database.MovieDatabase
import com.example.challenge4.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var api: MovieAPIService,
    var db: MovieDatabase.AppDatabase
) : ViewModel() {

    init {
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    private val _popularMovieList: MutableState<List<Movie>> = mutableStateOf(mutableListOf())
    val popularMovieList: State<List<Movie>> = _popularMovieList

    private val _topRatedMovieList: MutableState<List<Movie>> = mutableStateOf(mutableListOf())
    val topRatedMovieList: State<List<Movie>> = _topRatedMovieList

    private val _upcomingMovieList: MutableState<List<Movie>> = mutableStateOf(mutableListOf())
    val upcomingMovieList: State<List<Movie>> = _upcomingMovieList

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Popular movies are pulled from the API.
                val response = api.getPopularMovies(page = 1).results

                // Favorite movies are pulled from the Room database.
                val favoriteIDs = db.movieDao().getAllIDs()

                // Movies are checked if they are among the favorite movies.
                for (item in response) {
                    if (favoriteIDs.contains(item.id)) {
                        item.isFavorite = true
                    }
                }

                // The movies are posted to LiveData.
                _popularMovieList.value = response
            } catch (e: IOException) {}
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Popular movies are pulled from the API.
                val response = api.getTopRatedMovies(page = 1).results

                // Favorite movies are pulled from the Room database.
                val favoriteIDs = db.movieDao().getAllIDs()

                // Movies are checked if they are among the favorite movies.
                for (item in response) {
                    if (favoriteIDs.contains(item.id)) {
                        item.isFavorite = true
                    }
                }

                // The movies are posted to LiveData.
                _topRatedMovieList.value = response
            } catch (e: IOException) {}
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Popular movies are pulled from the API.
                val response = api.getUpcomingMovies(page = 1).results

                // Favorite movies are pulled from the Room database.
                val favoriteIDs = db.movieDao().getAllIDs()

                // Movies are checked if they are among the favorite movies.
                for (item in response) {
                    if (favoriteIDs.contains(item.id)) {
                        item.isFavorite = true
                    }
                }

                // The movies are posted to LiveData.
                _upcomingMovieList.value = response
            } catch (e: IOException) {}
        }
    }

    fun changeFavorite(movie: Movie) {
        /* When a movie is added to favorites,
        the isFavorite field is set to true and inserted into the Room database. */
        if (!movie.isFavorite) {
            movie.isFavorite = true
            viewModelScope.launch(Dispatchers.IO) {
                db.movieDao().insert(movie)
            }
        }
        /* When a movie is removed from favorites,
        the isFavorite field is set to false and removed from the Room database. */
        else {
            movie.isFavorite = false
            viewModelScope.launch(Dispatchers.IO) {
                db.movieDao().delete(movie)
            }
        }
    }
}
