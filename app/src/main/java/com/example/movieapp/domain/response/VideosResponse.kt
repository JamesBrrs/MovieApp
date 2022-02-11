package com.example.movieapp.domain.response

data class VideosResponse (
    val id: Long,
    val results: List<ResultVideo>
)

data class ResultVideo (
    val iso639_1: String,
    val iso3166_1: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Long,
    val type: String,
    val official: Boolean,
    val publishedAt: String,
    val id: String
)
