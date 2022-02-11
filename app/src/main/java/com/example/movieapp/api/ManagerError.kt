package com.example.movieapp.api

import android.content.Context
import android.util.Log
import com.example.movieapp.domain.models.BaseResponse
import com.google.gson.Gson
import retrofit2.Response

object ManagerError {

    fun <T> getMessageError(response: Response<T>, context: Context): String {
        var message = ""
        try {
            val data = Gson().fromJson(response.errorBody()?.string(), BaseResponse::class.java)
            Log.e("Response Data",data.toString())
            message = data.status_message
        } catch (e: Exception) {
            Log.e("getMessageError",e.message.toString())
        }
        return message
    }

}

