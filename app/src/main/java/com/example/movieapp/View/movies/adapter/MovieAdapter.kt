package com.example.movieapp.View.movies.adapter

import android.app.Activity
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.api.ApiConstants
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.domain.models.MovieModel

class MovieAdapter(private val activity: Activity, private val movies: List<MovieModel>,
                   private val onItemClick: OnItemMovieClickListener): RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private val time: Long = 1500
    private var lastClick: Long = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.movie_item, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.renderMovie(activity,movies[position],onItemClick)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieHolder(val view: View):RecyclerView.ViewHolder(view){
        fun renderMovie(activity: Activity, movie: MovieModel, action: OnItemMovieClickListener){
            val binding = MovieItemBinding.bind(view)
            val image = ApiConstants.BASE_IMAGE_URL+movie.posterPath
            Glide.with(activity).load(image).into(binding.portadaImage)
            binding.portadaImage.setOnClickListener {
                if (SystemClock.elapsedRealtime() - lastClick < time){
                    return@setOnClickListener
                }
                lastClick= SystemClock.elapsedRealtime()
                action.onItemMovieClick(movie)
            }
        }
    }

    interface OnItemMovieClickListener{
        fun onItemMovieClick(movie: MovieModel)
    }

}