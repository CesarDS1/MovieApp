package com.cesar.domain.interactors

import com.cesar.domain.model.Video
import com.cesar.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetVideoInformationUseCaseUseCase @Inject constructor(private val moviesRepository: MoviesRepository) :
    IGetVideoInformationUseCase {

    override fun invoke(movieId: Int): Single<List<Video>> {
        return moviesRepository.getVideoInformation(movieId)
    }

}