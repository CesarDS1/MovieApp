package com.cesar.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieInformation(
    var popularity: Double,
    @SerializedName("vote_count") var voteCount: Int,
    var video: Boolean,
    @SerializedName("poster_path") var posterPath: String?,
    var id: Int,
    var title: String,
    var overview: String,
    @SerializedName("release_date") var releaseDate: String
)