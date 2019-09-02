package com.cesar.domain.interactors

import com.cesar.domain.model.Movie
import com.cesar.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val repository: MoviesRepository) :
    ISearchMoviesUseCase {

    override fun invoke(querySearch: String): Single<List<Movie>> {
        return repository.searchMovies(querySearch)
    }

}