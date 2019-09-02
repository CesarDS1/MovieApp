package com.cesar.domain.services

import com.cesar.domain.model.Movie
import com.cesar.domain.model.Video
import io.reactivex.Single

interface MoviesServices {

    fun getTopRated(page: Int): Single<List<Movie>>

    fun getUpcoming(page: Int): Single<List<Movie>>

    fun getPopularMovies(page: Int): Single<List<Movie>>

    fun searchMovies(querySearch: String): Single<List<Movie>>

    fun getVideoInformation(movieId: Int):Single<List<Video>>
}