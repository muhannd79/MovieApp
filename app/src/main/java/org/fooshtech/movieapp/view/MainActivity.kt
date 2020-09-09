package org.fooshtech.movieapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.fooshtech.movieapp.R
import org.fooshtech.movieapp.adapter.MovieAdapter
import org.fooshtech.movieapp.model.Movie
import org.fooshtech.movieapp.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var movieViewModel: MovieViewModel
    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv_list_movies.layoutManager = LinearLayoutManager(this)
        rv_list_movies.setHasFixedSize(true)
        adapter = MovieAdapter { selectedMovie: Movie ->
            listItemClicked(selectedMovie)
        }
        rv_list_movies.adapter = adapter

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        movieViewModel.getListMovies().observe(this, Observer {
            showLogin(false)
            adapter.setDat(it)
        })

        initSearchView()


    }


    private fun listItemClicked(movie: Movie) {
        val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
        intent.putExtra("movie_info", movie)
        startActivity(intent)
    }

    private fun initSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                showLogin(true)
                movieViewModel.getSearchMovie(query)
                search_view.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        search_view.setOnCloseListener {
            search_view.clearFocus()
            showLogin(true)
            movieViewModel.getMovies()
            false
        }

    }

    fun showLogin(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

}


