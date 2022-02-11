package com.example.movieapp.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.ActionBar
import com.example.movieapp.domain.models.MovieModel
import com.google.android.material.tabs.TabLayout

class DataDBHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION){

    private val db: SQLiteDatabase = this.writableDatabase
    private val values : ContentValues = ContentValues()

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "MovieAPP_DB"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Movie(_id INTEGER PRIMARY KEY AUTOINCREMENT, posterPath TEXT, overview TEXT, releaseDate TEXT, id LONG ,backdropPath TEXT, title TEXT, type TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Movie")
        onCreate(db)
    }

    fun cleanDB(){
        db.execSQL("DROP TABLE IF EXISTS Movie")
        db.execSQL("CREATE TABLE Movie(_id INTEGER PRIMARY KEY AUTOINCREMENT, posterPath TEXT, overview TEXT, releaseDate TEXT, id LONG ,backdropPath TEXT, title TEXT, type TEXT)")
    }

    fun addMovie(movie: MovieModel){
        values.clear()
        values.put(Tables.Movie.COLUMN_BACKDROPPATH, movie.backdropPath)
        values.put(Tables.Movie.COLUMN_ID, movie.id)
        values.put(Tables.Movie.COLUMN_ORIGINALTITLE,movie.title)
        values.put(Tables.Movie.COLUMN_TYPE,movie.type)
        values.put(Tables.Movie.COLUMN_OVERVIEW,movie.overview)
        values.put(Tables.Movie.COLUMN_RELEASEDATE,movie.releaseDate)
        values.put(Tables.Movie.COLUMN_POSTERPATH,movie.posterPath)
        db.insert(Tables.Movie.TABLE_NAME,null,values)
    }

    fun updateMovie(movie: MovieModel){
        values.clear()
        values.put(Tables.Movie.COLUMN_BACKDROPPATH, movie.backdropPath)
        values.put(Tables.Movie.COLUMN_ID, movie.id)
        values.put(Tables.Movie.COLUMN_ORIGINALTITLE,movie.title)
        values.put(Tables.Movie.COLUMN_TYPE,movie.type)
        values.put(Tables.Movie.COLUMN_OVERVIEW,movie.overview)
        values.put(Tables.Movie.COLUMN_RELEASEDATE,movie.releaseDate)
        values.put(Tables.Movie.COLUMN_POSTERPATH,movie.posterPath)
        db.update(Tables.Movie.TABLE_NAME,values,"id ='${movie.id}'",null)
    }

    fun deleteMovie(id: Long){
        db.delete(Tables.Movie.TABLE_NAME, "id ='$id'",null)
    }
}