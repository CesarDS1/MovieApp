package com.cesar.data.services

import com.cesar.data.network.RetrofitInstance
import com.cesar.data.network.model.MoviesResponse
import com.cesar.data.network.model.mappers.MovieInformationResponseToMoviesMapper
import com.cesar.domain.services.MoviesServices
import com.cesar.domain.model.Movie
import com.cesar.domain.model.Video
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieServicesImpl @Inject constructor() : MoviesServices {


    private val apiKey = "b23df91d493ef919bbd4344cecccc57d"
    private val language = "en-US"

    override fun getTopRated(page: Int): Single<List<Movie>> {
        return RetrofitInstance.getMoviesApi().getTopRated(apiKey, language, page.toString()).map {
            mapMoviesResponseToMoviesList(it)
        }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    override fun getUpcoming(page: Int): Single<List<Movie>> {
        return RetrofitInstance.getMoviesApi().getUpcoming(apiKey, language, page.toString()).map {
            mapMoviesResponseToMoviesList(it)
        }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    override fun getPopularMovies(page: Int): Single<List<Movie>> {
        return RetrofitInstance.getMoviesApi().getPopularMovies(apiKey, language, page.toString())
            .map {
                mapMoviesResponseToMoviesList(it)
            }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    override fun searchMovies(querySearch: String): Single<List<Movie>> {
        return RetrofitInstance.getMoviesApi().searchMovies(apiKey, language, "1", querySearch)
            .map {
                mapMoviesResponseToMoviesList(it)
            }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    private fun mapMoviesResponseToMoviesList(moviesResponse: MoviesResponse): List<Movie> {
        return moviesResponse.results.map { movieInfo ->
            MovieInformationResponseToMoviesMapper.transform(movieInfo)
        }
    }

    override fun getVideoInformation(movieId: Int): Single<List<Video>> {
        return RetrofitInstance.getMoviesApi().getVideoMovieInformation(movieId, apiKey)
            .map { videoInformationResponse ->
                videoInformationResponse.results.map {
                    Video(
                        it.id,
                        it.iso_639_1,
                        it.iso_3166_1,
                        it.key,
                        it.name,
                        it.size,
                        it.site,
                        it.type
                    )
                }
            }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }
}