package com.example.movieapp.utils

import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.View.movies.MoviesInterface
import com.example.movieapp.View.movies.adapter.CastAdapter
import com.example.movieapp.View.movies.adapter.ProviderAdapter
import com.example.movieapp.api.ApiConstants
import com.example.movieapp.domain.models.MovieModel
import com.example.movieapp.domain.response.CastResponse
import com.example.movieapp.domain.response.DataProviders
import com.example.movieapp.domain.response.ResultVideo

object DialogMovie: MoviesInterface.MoviesView{

    fun setMovieData(view: View,movie: MovieModel, providers: List<DataProviders>?, cast: CastResponse?, video: List<ResultVideo>?, context: Context, activity: Activity){
        if (cast != null){
            view.findViewById<TextView>(R.id.cast_txt).visibility = View.VISIBLE
            setCast(view.findViewById(R.id.cast_recycler),context,activity,cast.cast)
        }
        if (providers != null) {
            if (providers.isNotEmpty()){
                setProvider(view.findViewById(R.id.Stream_txt),view.findViewById(R.id.provider_recycler),context,activity,providers)
            }
        }
        val url = ApiConstants.BASE_IMAGE_URL+movie.backdropPath
        view.findViewById<TextView>(R.id.title_txt).text = movie.title
        view.findViewById<TextView>(R.id.reseña_txt).text = movie.overview
        Glide.with(activity).load(url).into(view.findViewById<ImageView>(R.id.image_movie))
        val intents = video?.let { setTrailer(it,view.findViewById(R.id.trailer_btn),view.findViewById(R.id.trailer_txt_info)) }
        if (intents != null) {
            if (intents.first!= null && intents.second != null){
                view.findViewById<ImageView>(R.id.trailer_btn).setOnClickListener {
                    try {
                        activity.startActivity(intents.first)
                    } catch (ex: ActivityNotFoundException) {
                        activity.startActivity(intents.second)
                    }
                }
            }
        }
    }

}