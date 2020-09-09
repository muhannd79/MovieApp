package org.fooshtech.movieapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.fooshtech.movieapp.model.Movie
import org.fooshtech.movieapp.model.MovieResponse
import org.fooshtech.movieapp.model.request.MovieRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieRepository {

    private val list = arrayListOf<Movie>()
    private val moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    fun getMovies() {
        Log.d("tmz", "NetWork called....")
        list.clear()
        MovieRetrofit.initRetrofit()
            .getMovieList("0ff7d181adf179877025d774de1e4909")
            .enqueue(object : Callback<MovieResponse> {

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>?
                ) {

                    // val movieList = response?.body()?.results?.size ?: 0
                    // Log.d("tmz", "size= ${movieList}")
                    response?.body()?.results?.let {
                        list.addAll(it)
                    }
                    moviesLiveData.postValue(list)
                }


                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

                }

            })
    }

    fun getSearchMovie(query: String) {
        list.clear()
        MovieRetrofit.initRetrofit()
            .getSearchMovie("0ff7d181adf179877025d774de1e4909", query)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>?
                ) {

                    response?.body()?.results?.let {
                        list.addAll(it)
                    }
                    moviesLiveData.postValue(list)
                }


                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

                }

            })
    }

    fun getListMovies(): MutableLiveData<List<Movie>> {
        return moviesLiveData
    }
}


