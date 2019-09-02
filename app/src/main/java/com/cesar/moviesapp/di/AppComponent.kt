package com.cesar.moviesapp.di

import android.app.Application
import com.cesar.data.di.DataModule
import com.cesar.domain.di.DomainModule
import com.cesar.moviesapp.PresentationApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        DataModule::class,
        DomainModule::class
    ]
)
interface AppComponent : AndroidInjector<PresentationApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: PresentationApp)
}