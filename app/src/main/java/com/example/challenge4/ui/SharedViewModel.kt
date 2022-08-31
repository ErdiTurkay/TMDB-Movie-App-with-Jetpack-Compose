package com.example.challenge4.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.challenge4.model.Movie

class SharedViewModel : ViewModel() {
    var movie by mutableStateOf<Movie?>(null)

    fun addMovie(newMovie: Movie) {
        movie = newMovie
    }
}
