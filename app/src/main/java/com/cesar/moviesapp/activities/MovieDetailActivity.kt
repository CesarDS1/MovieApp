package com.cesar.moviesapp.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.cesar.domain.Constants.MOVIE_KEY
import com.cesar.moviesapp.R
import com.cesar.moviesapp.mvp.model.MovieDetail
import com.cesar.moviesapp.mvp.presenter.MovieDetailPresenter
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject


class MovieDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var movieDetailPresenter: MovieDetailPresenter

    lateinit var posterDetailIv: ImageView
    lateinit var titleDetailTv: TextView
    lateinit var overviewDetailTv: TextView
    lateinit var releaseDateDetailTv: TextView
    lateinit var movieVideoView: YouTubePlayerSupportFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        AndroidInjection.inject(this)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        posterDetailIv = imageView_poster_detail
        titleDetailTv = textView_title_detail
        overviewDetailTv = textView_overview_detail
        releaseDateDetailTv = textView_release_date_detail

        movieVideoView =
            supportFragmentManager.findFragmentById(R.id.video_movie) as YouTubePlayerSupportFragment


        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val data = intent.extras
        val movieDetail = data?.getParcelable<MovieDetail>(MOVIE_KEY)
        movieDetail?.let {
            movieDetailPresenter.showMovieDetailInformation(it)
            movieDetailPresenter.getVideoInformation(it)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                this.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val API_KEY = "AIzaSyDhfEBA7pokt28NzXKfK5KG-f1LxDNFLTE"
    }
}
