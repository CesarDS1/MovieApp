package com.cesar.domain.repository

import com.cesar.domain.model.Movie

interface MoviesRealmDataSource {

    fun saveMovieByCategory(category: String, movies: List<Movie>)

    fun getMoviesByCategory(category: String): List<Movie>
}