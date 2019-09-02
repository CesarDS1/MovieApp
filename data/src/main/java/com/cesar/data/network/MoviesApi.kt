package com.cesar.data.network

import com.cesar.data.network.model.MoviesResponse
import com.cesar.data.network.model.VideoInformationResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Single<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Single<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Single<MoviesResponse>

    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String,
        @Query("query") querySearch: String
    ): Single<MoviesResponse>

    @GET("movie/{movie_id}/videos")
    fun getVideoMovieInformation(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<VideoInformationResponse>

}