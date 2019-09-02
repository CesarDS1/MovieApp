package com.cesar.domain.repository

import com.cesar.domain.model.Movie
import com.cesar.domain.model.Video
import io.reactivex.Single

interface MoviesRepository {
    fun getTopRatedMoviesFromService(page: Int): Single<List<Movie>>
    fun getUpcomingMoviesFromService(page: Int): Single<List<Movie>>
    fun getPopularMoviesFromService(page: Int): Single<List<Movie>>
    fun searchMovies(querySearch: String): Single<List<Movie>>
    fun getMovieByCategoryFromDataBase(category: String): List<Movie>
    fun getVideoInformation(movieId: Int): Single<List<Video>>
}