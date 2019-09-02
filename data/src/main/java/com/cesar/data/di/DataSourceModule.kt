package com.cesar.data.di

import com.cesar.data.realm.MoviesRealmDataSourceImpl
import com.cesar.domain.repository.MoviesRealmDataSource
import dagger.Binds
import dagger.Module

@Module
internal abstract class DataSourceModule{
    @Binds
    abstract fun moviesRealmDataSource(implementation: MoviesRealmDataSourceImpl): MoviesRealmDataSource
}