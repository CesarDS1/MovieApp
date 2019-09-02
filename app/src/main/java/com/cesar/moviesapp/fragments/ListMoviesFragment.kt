package com.cesar.moviesapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cesar.domain.Constants.SECTION
import com.cesar.domain.model.Movie
import com.cesar.moviesapp.R
import com.cesar.moviesapp.adapter.ClickItem
import com.cesar.moviesapp.mvp.presenter.ListMoviesPresenter
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class ListMoviesFragment : DaggerFragment(), ClickItem {

    @Inject
    lateinit var listMoviesPresenter: ListMoviesPresenter

    lateinit var moviesRecyclerView: RecyclerView
    lateinit var searchEditText: EditText
    lateinit var moviesSwipeRefresh: SwipeRefreshLayout

    lateinit var section: String

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        section = arguments?.getString(SECTION).toString()
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesRecyclerView = rv_movies
        searchEditText = editText_search
        moviesSwipeRefresh = swipe_refresh_movies
        listMoviesPresenter.initViews(section)
        listMoviesPresenter.showSection(section)
        initSwipeToRefresh()
    }

    private fun initSwipeToRefresh() {
        moviesSwipeRefresh.setOnRefreshListener {
            if (listMoviesPresenter.isLoading.not()) {
                listMoviesPresenter.loadMoreMovies(section)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listMoviesPresenter.disposeSubscribers()
    }

    override fun onClickItem(movie: Movie) {
        listMoviesPresenter.goToMovieDetailActivity(movie)
    }

    companion object {
        fun newInstance(): ListMoviesFragment = ListMoviesFragment()
    }
}