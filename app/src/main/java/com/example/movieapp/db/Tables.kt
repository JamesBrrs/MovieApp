package com.example.movieapp.db

class Tables {

    abstract class Movie {

        companion object{
            val TABLE_NAME = "Movie"
            val _ID = "_id"
            val COLUMN_TYPE = "type"
            val COLUMN_POSTERPATH = "posterPath"
            val COLUMN_OVERVIEW = "overview"
            val COLUMN_RELEASEDATE = "releaseDate"
            val COLUMN_ID = "id"
            val COLUMN_BACKDROPPATH = "backdropPath"
            val COLUMN_ORIGINALTITLE = "title"
        }
    }
}