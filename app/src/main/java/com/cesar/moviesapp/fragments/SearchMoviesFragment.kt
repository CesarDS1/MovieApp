package com.cesar.moviesapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.cesar.domain.model.Movie
import com.cesar.moviesapp.R
import com.cesar.moviesapp.adapter.ClickItem
import com.cesar.moviesapp.mvp.presenter.SearchMoviesPresenter
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_search_movies.*
import javax.inject.Inject

class SearchMoviesFragment : DaggerFragment(), ClickItem {

    @Inject
    lateinit var searchMoviesPresenter: SearchMoviesPresenter

    lateinit var searchMoviesRv: RecyclerView
    lateinit var searchQueryEt: EditText
    lateinit var searchImg: ImageButton

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search_movies, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchMoviesRv = rv_movies_search_result
        searchQueryEt = editText_search
        searchImg = imageView_search

        searchMoviesPresenter.setTitle()
        searchImg.setOnClickListener {
            searchMoviesPresenter.searchMovies(searchQueryEt.text.toString())
        }

    }

    override fun onStop() {
        super.onStop()
        searchMoviesPresenter.disposeObservers()
    }

    override fun onClickItem(movie: Movie) {
        searchMoviesPresenter.goToMovieDetailActivity(movie)
    }

}