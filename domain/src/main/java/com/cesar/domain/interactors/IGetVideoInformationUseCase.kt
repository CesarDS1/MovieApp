package com.cesar.domain.interactors

import com.cesar.domain.model.Video
import io.reactivex.Single

interface IGetVideoInformationUseCase {
    operator fun invoke(movieId: Int): Single<List<Video>>
}