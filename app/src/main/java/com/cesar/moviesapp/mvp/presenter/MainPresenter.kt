package com.cesar.moviesapp.mvp.presenter

import com.cesar.moviesapp.mvp.view.MainView
import javax.inject.Inject

class MainPresenter @Inject constructor(private val mainView: MainView){

    fun initViews(){
        mainView.initBottomNavigation()
    }
}