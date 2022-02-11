package com.example.movieapp.db

import android.content.Context
import com.example.movieapp.domain.models.MovieModel
import com.example.movieapp.domain.response.BaseMovieResponse
import com.example.movieapp.utils.Constants

object DataBaseAdapter {

    fun getMovies(db: DataDBHelper, context: Context): List<MovieModel>{
        val movies = mutableListOf<MovieModel>()
        val cursor = db.readableDatabase.rawQuery("SELECT * FROM Movie",null )
        if (cursor != null){
            if (cursor.moveToFirst()){
                do {
                    val posterPath = cursor.getString(1)
                    val overview = cursor.getString(2)
                    val releaseDate = cursor.getString(3)
                    val id = cursor.getLong(4)
                    val backdropPath = cursor.getString(5)
                    val title = cursor.getString(6)
                    val type = cursor.getString(7)
                    val dbMovie = MovieModel(posterPath,overview,releaseDate,id,title,backdropPath,type)
                    movies.add(dbMovie)
                } while (cursor.moveToNext())
            }
        }
        return movies
    }

    fun existMovie(db: DataDBHelper, context: Context, id: Long): Boolean{
        var exist = false
        val movies = getMovies(db,context)
        for (i in movies.indices){
            if (movies[i].id == id)
                exist = true
        }
        return exist
    }

    fun getPopularMovies(db: DataDBHelper,context: Context): List<MovieModel>{
        val movies = getMovies(db,context)
        val popular = mutableListOf<MovieModel>()
        for (i in movies.indices){
            if (movies[i].type == Constants.TYPE_POPULAR)
                popular.add(movies[i])
        }
        return popular
    }

    fun getPlayingMovies(db: DataDBHelper,context: Context): List<MovieModel>{
        val movies = getMovies(db,context)
        val playing = mutableListOf<MovieModel>()
        for (i in movies.indices){
            if (movies[i].type == Constants.TYPE_PLAYING)
                playing.add(movies[i])
        }
        return playing
    }

    fun getUpcomingMovies(db: DataDBHelper,context: Context): List<MovieModel>{
        val movies = getMovies(db,context)
        val upcoming = mutableListOf<MovieModel>()
        for (i in movies.indices){
            if (movies[i].type == Constants.TYPE_UPCOMING)
                upcoming.add(movies[i])
        }
        return upcoming
    }

    fun getTop(db: DataDBHelper,context: Context): List<MovieModel>{
        val movies = getMovies(db,context)
        val top = mutableListOf<MovieModel>()
        for (i in movies.indices){
            if (movies[i].type == Constants.TYPE_TOP)
                top.add(movies[i])
        }
        return top
    }

    fun addOrUpdateMovie(db: DataDBHelper, context: Context, movie: MovieModel){
        if (movie.id?.let { existMovie(db,context, it) } == true)
            db.updateMovie(movie)
        else
            db.addMovie(movie)
    }

    fun deleteMovie(db: DataDBHelper, context: Context, id: Long){
        if (existMovie(db,context,id))
            db.deleteMovie(id)
    }

    fun addMovies(db: DataDBHelper, context: Context,type: String, data : BaseMovieResponse?){
        if (data != null){
            for (i in data.results.indices){
                val movie = MovieModel(data.results[i].poster_path,
                    data.results[i].overview,
                    data.results[i].releaseDate,
                    data.results[i].id,
                    data.results[i].title,
                    data.results[i].backdrop_path,
                    type)
                addOrUpdateMovie(db,context,movie)
            }
        }
    }

}