package com.cesar.domain.interactors

import com.cesar.domain.Constants
import com.cesar.domain.model.Movie
import com.cesar.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) :
    IGetPopularMoviesUseCase {

    override fun invoke(page: Int): Single<List<Movie>> {
        return Single.create<List<Movie>> { emitter ->
            run {
                moviesRepository.getPopularMoviesFromService(page).subscribe({
                    emitter.onSuccess(it)
                }, {
                    emitter.onSuccess(moviesRepository.getMovieByCategoryFromDataBase(Constants.CATEGORY_POPULAR))
                })
            }
        }
    }

}