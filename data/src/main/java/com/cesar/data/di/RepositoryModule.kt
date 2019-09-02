package com.cesar.data.di

import com.cesar.data.repository.MoviesRepositoryImpl
import com.cesar.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class RepositoryModule {
    @Binds
    abstract fun getMoviesRespository(implementation: MoviesRepositoryImpl): MoviesRepository
}