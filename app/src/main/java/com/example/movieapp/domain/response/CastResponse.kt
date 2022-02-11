package com.example.movieapp.domain.response

data class CastResponse (
    val id: Int,
    val cast: List<Cast>
)

data class Cast(
    val character: String,
    val name: String,
    val profile_path: String
)
