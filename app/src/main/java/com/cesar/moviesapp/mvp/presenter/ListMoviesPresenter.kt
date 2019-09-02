package com.cesar.moviesapp.mvp.presenter

import android.util.Log
import com.cesar.domain.Constants.POPULAR
import com.cesar.domain.Constants.RATED
import com.cesar.domain.Constants.UPCOMING
import com.cesar.domain.interactors.*
import com.cesar.domain.model.Movie
import com.cesar.moviesapp.mvp.model.MovieToMovieDetailMapper
import com.cesar.moviesapp.mvp.view.ListMoviesView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ListMoviesPresenter @Inject constructor(
    private val listMoviesView: ListMoviesView,
    private val getTopRatedUseCase: IGetTopRatedMoviesUseCase,
    private val getPopularMoviesUseCase: IGetPopularMoviesUseCase,
    private val getUpcomingMoviesUseCase: IGetUpcomingMoviesUseCase
) {
    private val compositeDisposable = CompositeDisposable()

    var isLoading = false
    var page = 1

    fun initViews(section: String) {
        listMoviesView.initRecyclerView(section)
    }

    fun showSection(section: String) {
        when (section) {
            RATED -> showTopRanted()
            POPULAR -> showPopular()
            UPCOMING -> showUpcoming()
        }
    }

    fun showTopRanted() {
        isLoading = true
        val subscribe = getTopRatedUseCase(page).subscribe({
            listMoviesView.updateListMovies(it)
            isLoading = false
        }, {
            isLoading = false
            Log.e(ListMoviesPresenter::class.java.simpleName, it.message)
        })
        compositeDisposable.add(subscribe)
    }

    fun showPopular() {
        isLoading = true
        val subscribe = getPopularMoviesUseCase(page).subscribe({
            listMoviesView.updateListMovies(it)
            isLoading = false
        }, {
            isLoading = false
            Log.e(ListMoviesPresenter::class.java.simpleName, it.message)
        })
        compositeDisposable.add(subscribe)
    }

    fun showUpcoming() {
        isLoading = true
        val subscribe = getUpcomingMoviesUseCase(page).subscribe({
            listMoviesView.updateListMovies(it)
            isLoading = false
        }, {
            isLoading = false
            Log.e(ListMoviesPresenter::class.java.simpleName, it.message)
        })
        compositeDisposable.add(subscribe)
    }

    fun loadMoreMovies(section: String) {
        page++
        when (section) {
            RATED -> showTopRanted()
            POPULAR -> showPopular()
            UPCOMING -> showUpcoming()
        }
    }

    fun disposeSubscribers() {
        if (compositeDisposable.size() != 0)
            compositeDisposable.dispose()
    }

    fun goToMovieDetailActivity(movie: Movie) {

        val movieForShare = MovieToMovieDetailMapper.transform(movie)
        listMoviesView.startDetailActivity(movieForShare)
    }

}