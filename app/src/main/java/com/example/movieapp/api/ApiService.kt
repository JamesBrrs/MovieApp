package com.example.movieapp.api

import com.example.movieapp.domain.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getPlayingNow(
        @Query("api_key") apiKey : String,
        @Query("page") page : Int
    ): Response<BaseMovieResponse>

    @GET("movie/popular")
    suspend fun getMostPopular(
        @Query("api_key") apiKey : String,
        @Query("page") page : Int
    ): Response<BaseMovieResponse>

    @GET("movie/top_rated")
    suspend fun getTop(
        @Query("api_key") apiKey : String,
        @Query("page") page : Int
    ): Response<BaseMovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpComing(
        @Query("api_key") apiKey : String,
        @Query("page") page : Int
    ): Response<BaseMovieResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideo(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey : String
    ): Response<VideosResponse>

    @GET("movie/{movie_id}/watch/providers")
    suspend fun getProviders(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey : String
    ): Response<ProvidersResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey : String
    ): Response<CastResponse>
}