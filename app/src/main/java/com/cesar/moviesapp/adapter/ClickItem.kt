package com.cesar.moviesapp.adapter

import com.cesar.domain.model.Movie

interface ClickItem {
    fun onClickItem(movie: Movie)
}