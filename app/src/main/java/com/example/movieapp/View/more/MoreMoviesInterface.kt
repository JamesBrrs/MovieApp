package com.example.movieapp.View.more

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.View.more.adapter.MoreAdapter
import com.example.movieapp.View.movies.adapter.CastAdapter
import com.example.movieapp.View.movies.adapter.MovieAdapter
import com.example.movieapp.View.splash.SlpashView
import com.example.movieapp.db.DataBaseAdapter
import com.example.movieapp.db.DataDBHelper
import com.example.movieapp.domain.models.MovieModel
import com.example.movieapp.domain.response.BaseMovieResponse
import com.example.movieapp.expose.NavigationExpose
import com.example.movieapp.utils.Constants
import kotlinx.coroutines.launch

interface MoreMoviesInterface: SlpashView.Movies {

    interface MoreView{

        fun setView(db: DataDBHelper,type: String, context: Context, activity: Activity, recyclerView: RecyclerView,
                    mAdapter: MoreAdapter.OnItemMovieMoreClickListener,lifeCycle: LifecycleCoroutineScope){
            var data: List<MovieModel>? = null
            when(type){
                Constants.TYPE_POPULAR-> data = DataBaseAdapter.getPopularMovies(db,context)
                Constants.TYPE_PLAYING-> data = DataBaseAdapter.getPlayingMovies(db,context)
                Constants.TYPE_UPCOMING-> data = DataBaseAdapter.getUpcomingMovies(db,context)
                Constants.TYPE_TOP-> data = DataBaseAdapter.getTop(db,context)
            }
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = layoutManager
            var adapterMovie: MoreAdapter? = null
            if (data != null) {
                adapterMovie = MoreAdapter(activity, data, mAdapter)
                recyclerView.adapter = adapterMovie
            }
        }

        fun newMovies(db: DataDBHelper, type: String, lifeCycle: LifecycleCoroutineScope, data: List<MovieModel>,
                      context: Context, adapter: MoreAdapter){
        }
    }
}