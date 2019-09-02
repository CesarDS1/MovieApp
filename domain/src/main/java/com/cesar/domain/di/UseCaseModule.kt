package com.cesar.domain.di

import com.cesar.domain.interactors.*
import dagger.Binds
import dagger.Module

@Module
internal abstract class UseCaseModule {

    @Binds
    abstract fun getTopRatedMoviesUseCase(implementation: GetTopRatedMoviesUseCase): IGetTopRatedMoviesUseCase

    @Binds
    abstract fun getUpcomingMoviesUseCase(implementation: GetUpcomingMoviesUseCase): IGetUpcomingMoviesUseCase

    @Binds
    abstract fun getPopularMoviesUseCase(implementation: GetPopularMoviesUseCase): IGetPopularMoviesUseCase

    @Binds
    abstract fun getSeachMoviesUseCase(implementation: SearchMoviesUseCase): ISearchMoviesUseCase

    @Binds
    abstract fun getVideoInformationUseCase(implementation: GetVideoInformationUseCaseUseCase): IGetVideoInformationUseCase
}