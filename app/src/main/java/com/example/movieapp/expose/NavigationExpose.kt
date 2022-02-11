package com.example.movieapp.expose

import android.content.Context
import android.util.Log
import com.example.movieapp.api.Resource
import com.example.movieapp.api.Source
import com.example.movieapp.domain.response.*
import java.security.Provider

class NavigationExpose {

    companion object{

        suspend fun getPlayingNow(context: Context,page: Int): BaseMovieResponse?{
            return try {
                val response = Source(context).getPlayingNow(page)
                if (response.status == Resource.Status.SUCCESS){
                    response.data
                }else
                    null
            }catch (e: Exception){
                Log.e("API ERROR","ERROR PLAYING NOW")
                return null
            }
        }

        suspend fun getMostPopular(context: Context, page: Int): BaseMovieResponse?{
            return try {
                val response = Source(context).getMostPopular(page)
                if (response.status == Resource.Status.SUCCESS){
                    response.data
                }else
                    null
            }catch (e: Exception){
                Log.e("API ERROR","ERROR MOST POPULAR")
                return null
            }
        }

        suspend fun getTop(context: Context, page: Int): BaseMovieResponse?{
            return try {
                val response = Source(context).getTop(page)
                if (response.status == Resource.Status.SUCCESS){
                    response.data
                }else
                    null
            }catch (e: Exception){
                Log.e("API ERROR","ERROR TOP")
                return null
            }
        }

        suspend fun getUpcoming(context: Context, page: Int): BaseMovieResponse?{
            return try {
                val response = Source(context).getUpComing(page)
                if (response.status == Resource.Status.SUCCESS){
                    response.data
                }else
                    null
            }catch (e: Exception){
                Log.e("API ERROR","ERROR UPCOMING")
                return null
            }
        }

        suspend fun getVideo(context: Context, id: Int): VideosResponse?{
            return try {
                val response = Source(context).getVideos(id)
                if (response.status == Resource.Status.SUCCESS){
                    response.data
                }else
                    null
            }catch (e: Exception){
                Log.e("API ERROR","ERROR VIDEO")
                return null
            }
        }

        suspend fun getProviders(context: Context, id: Int): ProvidersResponse?{
            return try {
                val response = Source(context).getProviders(id)
                if (response.status == Resource.Status.SUCCESS){
                    response.data
                }else
                    null
            }catch (e: Exception){
                Log.e("API ERROR","ERROR PROVIDERS")
                return null
            }
        }

        suspend fun getCast(context: Context, id: Int): CastResponse?{
            return try {
                val response = Source(context).getCast(id)
                if (response.status == Resource.Status.SUCCESS){
                    response.data
                }else
                    null
            }catch (e: Exception){
                Log.e("API ERROR","ERROR CAST")
                return null
            }
        }
    }
}