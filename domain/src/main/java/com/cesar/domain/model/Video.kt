package com.cesar.domain.model

data class Video(
    var id: String,
    var iso_639_1: String,
    var iso_3166_1: String,
    var key: String,
    var name: String,
    var size: Int,
    var site: String,
    var type: String
)