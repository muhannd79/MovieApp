package org.fooshtech.movieapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_movie.*
import org.fooshtech.movieapp.R
import org.fooshtech.movieapp.model.Movie

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        if( intent.hasExtra("movie_info")){

        val movieDetail = intent.getParcelableExtra<Movie>("movie_info")

            Log.d("tmz", "the info is=${movieDetail!!.title}")


           val image = movieDetail.poster_path
           val name = movieDetail.title

            val poster = "https://image.tmdb.org/t/p/w500$image"

            Glide.with(this)
                .load(poster)
                .placeholder(R.drawable.no_image)
                .into(img_movie_poster)

            txt_movie_title.text= movieDetail.title
            txt_movie_rating.text = movieDetail.vote_average.toString()
            txt_release_date.text = movieDetail.release_date
            txt_plotsynopsis.text = movieDetail.overview
        }
    }
}