package com.cesar.domain.interactors

import com.cesar.domain.model.Movie
import io.reactivex.Single

interface IGetPopularMoviesUseCase {
    operator fun invoke(page: Int): Single<List<Movie>>
}