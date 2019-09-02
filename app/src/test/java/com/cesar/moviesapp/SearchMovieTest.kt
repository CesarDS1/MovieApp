package com.cesar.moviesapp

import com.cesar.domain.interactors.SearchMoviesUseCase
import com.cesar.domain.model.Movie
import com.cesar.moviesapp.mvp.presenter.SearchMoviesPresenter
import com.cesar.moviesapp.mvp.view.SearchMoviesView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SearchMovieTest {

    private lateinit var searchMoviesPresenter: SearchMoviesPresenter

    @Mock
    private lateinit var searchMoviesView: SearchMoviesView
    @Mock
    private lateinit var searchMoviesUseCase: SearchMoviesUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        searchMoviesPresenter = SearchMoviesPresenter(searchMoviesView, searchMoviesUseCase)
    }

    @Test
    fun searchMoviesTest() {
        whenever(searchMoviesUseCase(any())).thenReturn(Single.just(moviesListMock))
        val test = searchMoviesUseCase("").test()
        searchMoviesPresenter.searchMovies("")
        test.assertNoErrors()
    }

    private val mockMovie =
        Movie(0.0, 0, false, "posterPath", 0, "Title", "Overview", "ReleaseDate")

    private val moviesListMock = mutableListOf(mockMovie, mockMovie, mockMovie)

}