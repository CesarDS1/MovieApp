package com.cesar.data.di

import com.cesar.data.services.MovieServicesImpl
import com.cesar.domain.services.MoviesServices
import dagger.Binds
import dagger.Module

@Module
internal abstract class ServiceModule {

    @Binds
    abstract fun getMoviesServices(implementation: MovieServicesImpl): MoviesServices
}