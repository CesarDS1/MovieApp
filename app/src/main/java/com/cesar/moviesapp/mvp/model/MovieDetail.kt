package com.cesar.moviesapp.mvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetail(
    var popularity: Double,
    var voteCount: Int,
    var video: Boolean,
    var posterPath: String?,
    var id: Int,
    var title: String,
    var overview: String,
    var releaseDate: String
) : Parcelable