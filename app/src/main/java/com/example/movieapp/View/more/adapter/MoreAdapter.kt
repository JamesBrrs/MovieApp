package com.example.movieapp.View.more.adapter

import android.app.Activity
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.api.ApiConstants
import com.example.movieapp.databinding.MovieMoreItemBinding
import com.example.movieapp.domain.models.MovieModel

class MoreAdapter (private val activity: Activity, private val movies: List<MovieModel>,
                   private val onItemClick: OnItemMovieMoreClickListener): RecyclerView.Adapter<MoreAdapter.MovieMoreHolder>() {

    private val time: Long = 1500
    private var lastClick: Long = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieMoreHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.movie_more_item, parent, false)
        return MovieMoreHolder(view)
    }

    override fun onBindViewHolder(holder: MovieMoreHolder, position: Int) {
        holder.renderMovieMore(activity,movies[position],onItemClick)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieMoreHolder(val view: View): RecyclerView.ViewHolder(view){
        fun renderMovieMore(activity: Activity, movie: MovieModel, action: OnItemMovieMoreClickListener){
            val binding = MovieMoreItemBinding.bind(view)
            val image = ApiConstants.BASE_IMAGE_URL+movie.backdropPath
            binding.tituloMovie.text = movie.title
            Glide.with(activity).load(image).into(binding.portadaMovie)
            binding.moreMovie.setOnClickListener {
                if (SystemClock.elapsedRealtime() - lastClick < time){
                    return@setOnClickListener
                }
                lastClick= SystemClock.elapsedRealtime()
                action.onItemMovieMoreClick(movie)
            }
        }
    }

    interface OnItemMovieMoreClickListener{
        fun onItemMovieMoreClick(movie: MovieModel)
    }

}