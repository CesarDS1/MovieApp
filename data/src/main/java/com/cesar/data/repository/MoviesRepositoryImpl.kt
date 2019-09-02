package com.cesar.data.repository

import com.cesar.domain.Constants
import com.cesar.domain.model.Movie
import com.cesar.domain.model.Video
import com.cesar.domain.repository.MoviesRealmDataSource
import com.cesar.domain.repository.MoviesRepository
import com.cesar.domain.services.MoviesServices
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieServices: MoviesServices,
    private val moviesRealmDataSource: MoviesRealmDataSource
) : MoviesRepository {

    override fun getMovieByCategoryFromDataBase(category: String): List<Movie> {
        val moviesByCategory = moviesRealmDataSource.getMoviesByCategory(category)
        if (moviesByCategory.isEmpty()) {
            //Default movie
            (moviesByCategory as MutableList).add(
                Movie(
                    0.0, 0,
                    false, "",
                    -1, Constants.NOT_FOUND,
                    "", ""
                )
            )
        }
        return moviesByCategory
    }

    override fun getTopRatedMoviesFromService(page: Int): Single<List<Movie>> {
        return movieServices.getTopRated(page).doOnSuccess {
            saveMovies(Constants.CATEGORY_TOP, it)
        }
    }

    override fun getUpcomingMoviesFromService(page: Int): Single<List<Movie>> {
        return movieServices.getUpcoming(page).doOnSuccess {
            saveMovies(Constants.CATEGORY_UPCOMING, it)
        }
    }

    override fun getPopularMoviesFromService(page: Int): Single<List<Movie>> {
        return movieServices.getPopularMovies(page).doOnSuccess {
            saveMovies(Constants.CATEGORY_POPULAR, it)
        }
    }

    override fun searchMovies(querySearch: String): Single<List<Movie>> =
        movieServices.searchMovies(querySearch)

    private fun saveMovies(category: String, movies: List<Movie>) {
        moviesRealmDataSource.saveMovieByCategory(category, movies)
    }

    override fun getVideoInformation(movieId: Int): Single<List<Video>> {
        return movieServices.getVideoInformation(movieId)
    }
}