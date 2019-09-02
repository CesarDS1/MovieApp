package com.cesar.data.network.model.mappers

import com.cesar.data.network.model.MovieInformation
import com.cesar.domain.model.Movie

object MovieInformationResponseToMoviesMapper {

    fun transform(movie: MovieInformation) = Movie(
        movie.popularity, movie.voteCount, movie.video, movie.posterPath,
        movie.id, movie.title, movie.overview, movie.releaseDate
    )
}