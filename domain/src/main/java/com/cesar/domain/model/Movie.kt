package com.cesar.domain.model

data class Movie(
    var popularity: Double,
    var voteCount: Int,
    var video: Boolean,
    var posterPath: String?,
    var id: Int,
    var title: String,
    var overview: String,
    var releaseDate: String
)