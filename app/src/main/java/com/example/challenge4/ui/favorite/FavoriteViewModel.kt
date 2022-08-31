package com.example.challenge4.ui.favorite

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge4.MovieAPIService
import com.example.challenge4.database.MovieDatabase
import com.example.challenge4.model.Actor
import com.example.challenge4.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    var api: MovieAPIService,
    var db: MovieDatabase.AppDatabase
) : ViewModel() {
    fun initialize() {
        getFavoriteActors()
        getFavoriteMovies()
    }

    private val _favoriteMovies: MutableState<List<Movie>> = mutableStateOf(mutableListOf())
    val favoriteMovies: State<List<Movie>> = _favoriteMovies

    private val _favoriteActors: MutableState<List<Actor>> = mutableStateOf(mutableListOf())
    val favoriteActors: State<List<Actor>> = _favoriteActors

    private fun getFavoriteMovies() {
        // Favorite movies are pulled from the Room database.
        viewModelScope.launch(Dispatchers.IO) {
            val value = db.movieDao().getAll()

            viewModelScope.launch(Dispatchers.Main) {
                _favoriteMovies.value = value
            }
        }
    }

    private fun getFavoriteActors() {
        // Favorite movies are pulled from the Room database.
        viewModelScope.launch(Dispatchers.IO) {
            val value = db.actorDao().getAll()

            viewModelScope.launch(Dispatchers.Main) {
                _favoriteActors.value = value
            }
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
