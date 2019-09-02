package com.cesar.moviesapp

import com.cesar.domain.interactors.*
import com.cesar.domain.model.Movie
import com.cesar.moviesapp.mvp.presenter.ListMoviesPresenter
import com.cesar.moviesapp.mvp.view.ListMoviesView
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ListMoviesPresenterTest {

    lateinit var listMoviesPresenter: ListMoviesPresenter

    @Mock
    private lateinit var listMoviesView: ListMoviesView
    @Mock
    private lateinit var getTopRatedUseCase: IGetTopRatedMoviesUseCase
    @Mock
    private lateinit var getPopularMoviesUseCase: IGetPopularMoviesUseCase
    @Mock
    private lateinit var getUpcomingMoviesUseCase: IGetUpcomingMoviesUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)

        listMoviesPresenter = ListMoviesPresenter(
            listMoviesView, getTopRatedUseCase, getPopularMoviesUseCase, getUpcomingMoviesUseCase
        )

    }

    @Test
    fun testGetTopRatedMoviesList() {

        whenever(getTopRatedUseCase(1)).thenReturn(Single.just(moviesListMock))
        val test = getTopRatedUseCase(1).test()
        listMoviesPresenter.showTopRanted()

        test.assertValue(moviesListMock).assertNoErrors()
    }

    @Test
    fun testGetPopularMoviesList() {

        whenever(getPopularMoviesUseCase(1)).thenReturn(Single.just(moviesListMock))

        val test = getPopularMoviesUseCase(1).test()

        listMoviesPresenter.showPopular()

        test.assertValue(moviesListMock).assertNoErrors()
    }

    @Test
    fun testUpcomingMoviesList() {

        whenever(getUpcomingMoviesUseCase(1)).thenReturn(Single.just(moviesListMock))

        val test = getUpcomingMoviesUseCase(1).test()

        listMoviesPresenter.showUpcoming()

        test.assertValue(moviesListMock).assertNoErrors()
    }

    @Test
    fun goToMovieDetailActivityTest() {
        listMoviesPresenter.goToMovieDetailActivity(mockMovie)
        verify(listMoviesView, atLeastOnce()).startDetailActivity(any())
    }

    private val mockMovie =
        Movie(0.0, 0, false, "posterPath", 0, "Title", "Overview", "ReleaseDate")

    private val moviesListMock = mutableListOf(mockMovie, mockMovie, mockMovie)

}