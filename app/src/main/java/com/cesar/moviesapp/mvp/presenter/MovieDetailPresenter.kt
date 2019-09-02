package com.cesar.moviesapp.mvp.presenter

import android.util.Log
import com.cesar.domain.interactors.IGetVideoInformationUseCase
import com.cesar.moviesapp.mvp.model.MovieDetail
import com.cesar.moviesapp.mvp.view.MovieDetailView
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor(
    private val movieDetailView: MovieDetailView,
    private val getVideInformationUseCaseUseCase: IGetVideoInformationUseCase
) {

    fun showMovieDetailInformation(movieDetail: MovieDetail) {
        movieDetailView.showMovieInformation(movieDetail)
    }

    fun getVideoInformation(movieDetail: MovieDetail) {
        val subscribe = getVideInformationUseCaseUseCase(movieDetail.id).subscribe({
            val filter = it.filter { video -> video.type == "Trailer" && video.site == "YouTube" }
            movieDetailView.showVideo(filter[0].key)
        }, {
            Log.e(MovieDetailPresenter::class.java.simpleName, it.message)
        })
    }
}