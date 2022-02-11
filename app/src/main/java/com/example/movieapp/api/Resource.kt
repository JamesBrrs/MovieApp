package com.example.movieapp.api

data class Resource<out T> (var status : Status, val data : T?, val message : String?, val code: Int) {

    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success( data : T, code: Int) : Resource<T> {
            return Resource(Status.SUCCESS , data , null,code)
        }

        fun <T> error( message : String , data : T? = null, code: Int) : Resource<T> {
            return Resource(Status.ERROR , data , message, code)
        }
    }
}