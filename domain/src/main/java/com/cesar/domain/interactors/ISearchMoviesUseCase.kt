package com.cesar.domain.interactors

import com.cesar.domain.model.Movie
import io.reactivex.Single

interface ISearchMoviesUseCase {
    operator fun invoke(querySearch: String): Single<List<Movie>>
}