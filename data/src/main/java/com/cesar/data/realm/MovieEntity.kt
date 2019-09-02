package com.cesar.data.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MovieEntity(
    var popularity: Double = 0.0,
    var voteCount: Int = 0,
    var video: Boolean = false,
    var posterPath: String? = "",
    @PrimaryKey var id: Int = 0,
    var title: String = "",
    var overview: String = "",
    var releaseDate: String = "",
    var category: String = " "
) : RealmObject() {
    companion object {
        const val CATEGORY = "category"
    }
}