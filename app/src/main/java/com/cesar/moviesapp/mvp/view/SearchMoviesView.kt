package com.cesar.moviesapp.mvp.view

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cesar.domain.Constants
import com.cesar.domain.model.Movie
import com.cesar.moviesapp.R
import com.cesar.moviesapp.activities.MainActivity
import com.cesar.moviesapp.activities.MovieDetailActivity
import com.cesar.moviesapp.adapter.MoviesAdapter
import com.cesar.moviesapp.fragments.SearchMoviesFragment
import com.cesar.moviesapp.mvp.model.MovieDetail
import javax.inject.Inject

class SearchMoviesView @Inject constructor(private val searchMoviesFragment: SearchMoviesFragment) {

    fun showTitle() {
        val supportActionBar = (searchMoviesFragment.activity as MainActivity).supportActionBar
        supportActionBar?.title = searchMoviesFragment.getString(R.string.search)
    }

    fun showMovies(movies: List<Movie>) {
        val moviesAdapter = MoviesAdapter(movies as ArrayList<Movie>)
        moviesAdapter.clickItem = searchMoviesFragment

        searchMoviesFragment.searchMoviesRv.adapter = moviesAdapter

        searchMoviesFragment.searchMoviesRv.layoutManager =
            LinearLayoutManager(searchMoviesFragment.activity)

        searchMoviesFragment.searchMoviesRv.setHasFixedSize(true)
    }

    fun startDetailActivity(movieDetail: MovieDetail) {
        val intent = Intent(searchMoviesFragment.activity, MovieDetailActivity::class.java)
        intent.putExtra(Constants.MOVIE_KEY, movieDetail)
        searchMoviesFragment.activity?.startActivity(intent)
    }

}