package com.example.movieapp.domain.response

data class BaseMovieResponse(
    val page: Long,
    val results: List<ResultMovie>,
    val totalResults: Long,
    val totalPages: Long
)

data class ResultMovie(
    val poster_path: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String,
    val genreIDS: List<Long>,
    val id: Long,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdrop_path: String,
    val popularity: Double,
    val voteCount: Long,
    val video: Boolean,
    val voteAverage: Double
)
