package com.cesar.moviesapp.mvp.presenter

import android.util.Log
import com.cesar.domain.interactors.SearchMoviesUseCase
import com.cesar.domain.model.Movie
import com.cesar.moviesapp.mvp.model.MovieToMovieDetailMapper
import com.cesar.moviesapp.mvp.view.SearchMoviesView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SearchMoviesPresenter @Inject constructor(
    private val searchMoviesView: SearchMoviesView,
    private val searchMoviesUseCase: SearchMoviesUseCase
) {
    private var subscribe: Disposable? = null

    fun setTitle(){
        searchMoviesView.showTitle()
    }

    fun searchMovies(searchQuery: String) {
        subscribe = searchMoviesUseCase(searchQuery).subscribe({
            searchMoviesView.showMovies(it)
        }, {
            Log.e(SearchMoviesPresenter::class.java.simpleName, it.message)
        })
    }

    fun disposeObservers() {
        subscribe?.dispose()
    }

    fun goToMovieDetailActivity(movie: Movie) {

        val movieForShare = MovieToMovieDetailMapper.transform(movie)
        searchMoviesView.startDetailActivity(movieForShare)

    }

}