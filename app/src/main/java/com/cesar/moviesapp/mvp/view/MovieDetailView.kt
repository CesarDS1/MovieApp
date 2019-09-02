package com.cesar.moviesapp.mvp.view

import android.widget.Toast
import com.cesar.domain.Constants
import com.cesar.moviesapp.activities.MovieDetailActivity
import com.cesar.moviesapp.mvp.model.MovieDetail
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MovieDetailView @Inject constructor(private val movieDetailActivity: MovieDetailActivity) {

    fun showMovieInformation(movieDetail: MovieDetail) {

        movieDetailActivity.supportActionBar?.title = movieDetail.title

        movieDetailActivity.titleDetailTv.text = movieDetail.title
        movieDetailActivity.overviewDetailTv.text = movieDetail.overview
        movieDetailActivity.releaseDateDetailTv.text = movieDetail.releaseDate

        Picasso.get()
            .load(Constants.BASE_URL_IMAGES + movieDetail.posterPath)
            .resize(300, 350)
            .centerCrop()
            .into(movieDetailActivity.posterDetailIv)
    }

    fun showVideo(keyVideo: String) {
        movieDetailActivity.movieVideoView.initialize(
            MovieDetailActivity.API_KEY, InitYouTubePlayer(keyVideo)
        )
    }

    private fun InitYouTubePlayer(keyVideo: String): OnInitializedListener {
        return object : OnInitializedListener {
            override fun onInitializationSuccess(
                playerProvider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {
                player?.loadVideo(keyVideo)
                player?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(movieDetailActivity, p1.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }

}