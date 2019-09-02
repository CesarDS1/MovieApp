package com.cesar.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cesar.domain.Constants
import com.cesar.domain.model.Movie
import com.cesar.moviesapp.R
import com.squareup.picasso.Picasso

class MoviesAdapter(private val movies: ArrayList<Movie>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var clickItem: ClickItem? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_movie, parent, false)
        return MoviesViewHolder(view)
    }


    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MoviesViewHolder).movieTitleTextView.text = movies[position].title

        holder.itemView.setOnClickListener {
            movies[position].let { movie ->
                clickItem?.onClickItem(
                    movie
                )
            }
        }

        Picasso.get()
            .load(Constants.BASE_URL_IMAGES + movies[position].posterPath)
            .resize(200, 250)
            .centerCrop()
            .into(holder.moviePosterImageView)

    }

    fun filterMovieList(moviesFiltered: ArrayList<Movie>) {
        movies.clear()
        movies.addAll(moviesFiltered)
        notifyDataSetChanged()
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviePosterImageView: ImageView = itemView.findViewById(R.id.imageView_poster_movie)
        val movieTitleTextView: TextView = itemView.findViewById(R.id.textView_title_movie)
    }

}