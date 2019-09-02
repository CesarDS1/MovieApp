package com.cesar.data.di

import dagger.Module

@Module(includes = [ServiceModule::class, RepositoryModule::class, DataSourceModule::class])
class DataModule