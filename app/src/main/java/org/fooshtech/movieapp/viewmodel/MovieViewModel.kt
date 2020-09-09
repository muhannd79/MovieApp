package org.fooshtech.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.fooshtech.movieapp.model.Movie
import org.fooshtech.movieapp.model.MovieResponse
import org.fooshtech.movieapp.repository.MovieRepository

class MovieViewModel : ViewModel() {

     private var repository  = MovieRepository()

    init {
        getMovies()
    }

     fun getMovies(){
        repository.getMovies()
    }

    fun getListMovies() : LiveData<List<Movie>>{

        return repository.getListMovies()
    }

    fun getSearchMovie(q : String) {
        repository.getSearchMovie(q)
    }

}