package org.fooshtech.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*
import org.fooshtech.movieapp.R
import org.fooshtech.movieapp.model.Movie

class MovieAdapter( private val clickListener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies: List<Movie>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.item_layout, parent, false)
        return MovieViewHolder(listItem)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies!![position], position, clickListener)
    }


    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    fun setDat(it: List<Movie>?) {
        movies = it
        notifyDataSetChanged()
    }


    class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie, position: Int, clickListener: (Movie) -> Unit) {

            view.txt_movie_title.text = movie.title
            view.txt_popularity.text = movie.popularity.toString()
            view.txt_year.text = movie.release_date

            Glide.with(view.imageView_item.context)
                .load("https://image.tmdb.org/t/p/w500/" + movie.backdrop_path)
                .placeholder(R.drawable.no_image)
                .into(view.imageView_item)

            view.setOnClickListener {
                clickListener(movie)
            }
        }

    }
}