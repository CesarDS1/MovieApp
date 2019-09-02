package com.cesar.moviesapp.mvp.view

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.cesar.domain.Constants
import com.cesar.domain.Constants.MOVIE_KEY
import com.cesar.domain.model.Movie
import com.cesar.moviesapp.R
import com.cesar.moviesapp.activities.MainActivity
import com.cesar.moviesapp.activities.MovieDetailActivity
import com.cesar.moviesapp.adapter.MoviesAdapter
import com.cesar.moviesapp.fragments.ListMoviesFragment
import com.cesar.moviesapp.mvp.model.MovieDetail
import javax.inject.Inject


class ListMoviesView @Inject constructor(private val listMoviesFragment: ListMoviesFragment) {

    lateinit var moviesOriginal: ArrayList<Movie>
    lateinit var moviesChanging: ArrayList<Movie>
    lateinit var movieAdapter: MoviesAdapter


    fun initRecyclerView(category: String) {

        moviesOriginal = ArrayList()
        moviesChanging = ArrayList()

        movieAdapter = MoviesAdapter(moviesOriginal)

        movieAdapter.clickItem = listMoviesFragment

        listMoviesFragment.moviesRecyclerView.adapter = movieAdapter
        listMoviesFragment.moviesRecyclerView.layoutManager =
            LinearLayoutManager(listMoviesFragment.activity)


        initSearchEditText()
        setTitleScreen(category)
    }

    fun updateListMovies(movies: List<Movie>) {
        listMoviesFragment.moviesSwipeRefresh.isRefreshing = false
        moviesOriginal.addAll(0,movies)
        moviesChanging.addAll(0, movies)
        movieAdapter.notifyDataSetChanged()

    }

    fun startDetailActivity(movieDetail: MovieDetail) {

        val intent = Intent(listMoviesFragment.activity, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_KEY, movieDetail)
        listMoviesFragment.activity?.startActivity(intent)

    }

    private fun initSearchEditText() {
        listMoviesFragment.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                filterMovies(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do nothing
            }

        })
    }

    private fun filterMovies(text: String) {
        if (text.isEmpty()) {
            movieAdapter.filterMovieList(moviesChanging)
        }
        val filteredMovies = mutableListOf<Movie>()

        for (movie in moviesChanging) {
            if (movie.title.toLowerCase().contains(text.toLowerCase())) {
                    movie.let { filteredMovies.add(it) }
                }
        }
        movieAdapter.filterMovieList(filteredMovies as ArrayList<Movie>)
    }

    private fun setTitleScreen(category: String) {
        val supportActionBar = (listMoviesFragment.activity as MainActivity).supportActionBar
        when (category) {
            Constants.RATED -> supportActionBar?.title =
                listMoviesFragment.getString(R.string.top_rated)
            Constants.POPULAR -> supportActionBar?.title =
                listMoviesFragment.getString(R.string.popular)
            Constants.UPCOMING -> supportActionBar?.title =
                listMoviesFragment.getString(R.string.upcoming)
        }
    }
}