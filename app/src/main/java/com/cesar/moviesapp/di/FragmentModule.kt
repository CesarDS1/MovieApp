package com.cesar.moviesapp.di

import com.cesar.moviesapp.fragments.ListMoviesFragment
import com.cesar.moviesapp.fragments.SearchMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesListMoviesFragment(): ListMoviesFragment
    @ContributesAndroidInjector
    abstract fun contributesSearchMoviesFragment(): SearchMoviesFragment
}