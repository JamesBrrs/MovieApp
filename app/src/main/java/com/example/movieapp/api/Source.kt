package com.example.movieapp.api

import android.content.Context
import com.example.movieapp.domain.response.*

class Source(override val context: Context) : BaseDataSource() {
    override fun getUrl() = ApiConstants.BASE_URL
    override fun getClazz() = ApiService::class.java

    suspend fun getPlayingNow(page: Int): Resource<BaseMovieResponse> {
        return getResult {
            getRetrofit<ApiService>().getPlayingNow(
                ApiConstants.API_KEY,
                page
            )
        }
    }

    suspend fun getMostPopular(page: Int): Resource<BaseMovieResponse> {
        return getResult {
            getRetrofit<ApiService>().getMostPopular(
                ApiConstants.API_KEY,
                page
            )
        }
    }

    suspend fun getTop(page: Int): Resource<BaseMovieResponse> {
        return getResult {
            getRetrofit<ApiService>().getTop(
                ApiConstants.API_KEY,
                page
            )
        }
    }

    suspend fun getUpComing(page: Int): Resource<BaseMovieResponse> {
        return getResult {
            getRetrofit<ApiService>().getUpComing(
                ApiConstants.API_KEY,
                page
            )
        }
    }

    suspend fun getVideos(id: Int): Resource<VideosResponse>{
        return getResult {
            getRetrofit<ApiService>().getVideo(
                id,
                ApiConstants.API_KEY
            )
        }
    }

    suspend fun getProviders(id: Int): Resource<ProvidersResponse>{
        return getResult {
            getRetrofit<ApiService>().getProviders(
                id,
                ApiConstants.API_KEY
            )
        }
    }

    suspend fun getCast(id: Int): Resource<CastResponse>{
        return getResult {
            getRetrofit<ApiService>().getCast(
                id,
                ApiConstants.API_KEY
            )
        }
    }

}