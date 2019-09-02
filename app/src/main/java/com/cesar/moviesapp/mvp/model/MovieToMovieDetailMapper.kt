package com.cesar.moviesapp.mvp.model

import com.cesar.domain.model.Movie

object MovieToMovieDetailMapper {

    fun transform(movie: Movie): MovieDetail{
        return MovieDetail(
            movie.popularity,
            movie.voteCount,
            movie.video,
            movie.posterPath,
            movie.id,
            movie.title,
            movie.overview,
            movie.releaseDate
        )
    }
}