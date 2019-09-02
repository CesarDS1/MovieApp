package com.cesar.domain.interactors

import com.cesar.domain.Constants
import com.cesar.domain.model.Movie
import com.cesar.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) :
    IGetUpcomingMoviesUseCase {

    override fun invoke(page: Int): Single<List<Movie>> {
        return Single.create<List<Movie>> { emitter ->
            moviesRepository.getUpcomingMoviesFromService(page).subscribe({
                emitter.onSuccess(it)
            }, {
                emitter.onSuccess(moviesRepository.getMovieByCategoryFromDataBase(Constants.CATEGORY_UPCOMING))
            })
        }
    }
}