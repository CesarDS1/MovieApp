package com.cesar.moviesapp.mvp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.cesar.domain.Constants.POPULAR
import com.cesar.domain.Constants.RATED
import com.cesar.domain.Constants.SECTION
import com.cesar.domain.Constants.UPCOMING
import com.cesar.moviesapp.R
import com.cesar.moviesapp.activities.MainActivity
import com.cesar.moviesapp.fragments.ListMoviesFragment
import com.cesar.moviesapp.fragments.SearchMoviesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainView @Inject constructor(private val mainActivity: MainActivity) {

    fun initBottomNavigation() {
        with(mainActivity.bottomNavigationBar) {
            setOnNavigationItemSelectedListener(getNavigationListener)
            selectedItemId = R.id.top_rated_movies
        }
    }

    private val getNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
        val bundle = Bundle()
        val listMoviesFragment = ListMoviesFragment.newInstance()
        when (it.itemId) {
            R.id.top_rated_movies -> {
                setArguments(listMoviesFragment, bundle, RATED)
                openFragment(listMoviesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.popular_movies -> {

                setArguments(listMoviesFragment, bundle, POPULAR)
                openFragment(listMoviesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.upcoming_movies -> {
                setArguments(listMoviesFragment, bundle, UPCOMING)
                listMoviesFragment.arguments = bundle
                openFragment(listMoviesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.search -> {
                changeFragment(SearchMoviesFragment::class.java.name)
                return@OnNavigationItemSelectedListener true
            }
            else -> return@OnNavigationItemSelectedListener false
        }
    }

    private fun setArguments(fragment: Fragment, bundle: Bundle, section: String) {
        bundle.putString(SECTION, section)
        fragment.arguments = bundle
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = mainActivity.supportFragmentManager.beginTransaction()
        with(transaction) {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }

    }

    private fun changeFragment(fragmentName: String) {
        val fragment = mainActivity.supportFragmentManager.findFragmentByTag(fragmentName)
            ?: Fragment.instantiate(mainActivity, fragmentName)

        val transaction = mainActivity.supportFragmentManager.beginTransaction()

        with(transaction) {
            replace(R.id.fragment_container, fragment, fragmentName)
            addToBackStack(null)
            commit()
        }

    }

}