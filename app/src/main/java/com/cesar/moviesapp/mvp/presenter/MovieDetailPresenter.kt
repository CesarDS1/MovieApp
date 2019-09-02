package com.cesar.moviesapp.mvp.presenter

import android.util.Log
import com.cesar.domain.interactors.IGetVideoInformationUseCase
import com.cesar.moviesapp.mvp.model.MovieDetail
import com.cesar.moviesapp.mvp.view.MovieDetailView
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor(
    private val movieDetailView: MovieDetailView,
    private val getVideoInformationUseCaseUseCase: IGetVideoInformationUseCase
) {

    fun showMovieDetailInformation(movieDetail: MovieDetail) {
        movieDetailView.showMovieInformation(movieDetail)
    }

    fun getVideoInformation(movieDetail: MovieDetail) {
        val subscribe = getVideoInformationUseCaseUseCase(movieDetail.id).subscribe({
            if (it.isNotEmpty()) {
                val filter =
                    it.filter { video -> video.type == "Trailer" && video.site == "YouTube" }
                if (filter.isNotEmpty()) {
                    movieDetailView.showVideo(filter[0].key)
                }
            }
        }, {
            Log.e(MovieDetailPresenter::class.java.simpleName, it.message)
        })
    }
}