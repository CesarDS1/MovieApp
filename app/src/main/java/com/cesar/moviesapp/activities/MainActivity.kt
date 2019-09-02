package com.cesar.moviesapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.cesar.moviesapp.R
import com.cesar.moviesapp.custom.BottomNavigationBehavior
import com.cesar.moviesapp.mvp.presenter.MainPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainPresenter: MainPresenter

    lateinit var bottomNavigationBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)

        bottomNavigationBar = findViewById(R.id.bottomNavigationView)

        with(bottomNavigationBar.layoutParams as CoordinatorLayout.LayoutParams) {
            behavior = BottomNavigationBehavior()
        }

        mainPresenter.initViews()
    }

}
