package com.cesar.domain.interactors

import com.cesar.domain.Constants
import com.cesar.domain.model.Movie
import com.cesar.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) :
    IGetTopRatedMoviesUseCase {

    override fun invoke(page: Int): Single<List<Movie>> {
        return Single.create<List<Movie>> {emitter->
            moviesRepository.getTopRatedMoviesFromService(page).subscribe({
                emitter.onSuccess(it)
            }, {
                emitter.onSuccess(moviesRepository.getMovieByCategoryFromDataBase(Constants.CATEGORY_TOP))
            })
        }
    }
}