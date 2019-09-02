package com.cesar.moviesapp.di

import com.cesar.moviesapp.activities.MainActivity
import com.cesar.moviesapp.activities.MovieDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesMovieDetailActivity(): MovieDetailActivity
}