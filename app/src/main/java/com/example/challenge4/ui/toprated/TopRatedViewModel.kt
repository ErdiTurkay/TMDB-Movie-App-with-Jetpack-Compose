package com.example.challenge4.ui.toprated

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
class TopRatedViewModel @Inject constructor(
    var api: MovieAPIService,
    var db: MovieDatabase.AppDatabase
) : ViewModel() {
    var page = 1
    var totalPage = 900
    var movieList = arrayListOf<Movie>()

    init {
        getTopRatedMovies()
    }

    private val _topRatedMovieList: MutableState<List<Movie>> = mutableStateOf(mutableListOf())
    val topRatedMovieList: State<List<Movie>> = _topRatedMovieList

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Popular movies are pulled from the API.
                val response = api.getTopRatedMovies(page = page)
                val popularList = response.results

                // Favorite movies are pulled from the Room database.
                val favoriteIDs = db.movieDao().getAllIDs()

                // Movies are checked if they are among the favorite movies.
                for (item in popularList) {
                    if (favoriteIDs.contains(item.id)) {
                        item.isFavorite = true
                    }
                }

                // The totalPage variable has been updated according to the totalPage field from the API.
                totalPage = response.totalPages

                // The movie list has been updated and posted to LiveData.
                movieList.addAll(popularList)
                _topRatedMovieList.value = movieList

                // The page variable is incremented by 1 for the other query.
                page++
            } catch (e: IOException) {
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
