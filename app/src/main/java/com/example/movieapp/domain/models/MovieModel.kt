package com.example.movieapp.domain.models

data class MovieModel (
    val posterPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val id: Long?,
    val title: String?,
    val backdropPath: String?,
    val type: String?
)