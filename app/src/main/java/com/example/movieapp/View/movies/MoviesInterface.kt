package com.example.movieapp.View.movies

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.View.more.MoreMoviesActivity
import com.example.movieapp.View.movies.adapter.CastAdapter
import com.example.movieapp.View.movies.adapter.MovieAdapter
import com.example.movieapp.View.movies.adapter.ProviderAdapter
import com.example.movieapp.db.DataBaseAdapter
import com.example.movieapp.db.DataDBHelper
import com.example.movieapp.domain.models.MovieModel
import com.example.movieapp.domain.response.Cast
import com.example.movieapp.domain.response.DataProviders
import com.example.movieapp.domain.response.ResultVideo
import com.example.movieapp.utils.Constants

interface MoviesInterface {

    interface MoviesView{

        fun setMovieView(type: String, db: DataDBHelper, context: Context, recyclerView: RecyclerView,
                         activity: Activity, mAdapter: MovieAdapter.OnItemMovieClickListener, more: TextView ){
            var data: List<MovieModel>? = null
            when(type){
                Constants.TYPE_POPULAR-> data = DataBaseAdapter.getPopularMovies(db,context)
                Constants.TYPE_PLAYING-> data = DataBaseAdapter.getPlayingMovies(db,context)
                Constants.TYPE_UPCOMING-> data = DataBaseAdapter.getUpcomingMovies(db,context)
                Constants.TYPE_TOP-> data = DataBaseAdapter.getTop(db,context)
            }
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = layoutManager
            if (data != null){
                val dataShow = mutableListOf<MovieModel>()
                if (data.size > 30){
                    for (i in 0..30)
                        dataShow.add(data[i])
                }
                val adapterMovie = MovieAdapter(activity,dataShow,mAdapter)
                recyclerView.adapter = adapterMovie
                recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        val find = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                        if (find == dataShow.size - 1)
                            more.visibility = View.VISIBLE
                        else
                            more.visibility = View.GONE
                    }
                })
                more.setOnClickListener {
                    val intent =  Intent(context,MoreMoviesActivity::class.java)
                    intent.putExtra("TYPE",type)
                    activity.startActivity(intent)
                }
            }
        }

        fun setCast(recyclerView: RecyclerView, context: Context, activity: Activity, cast: List<Cast>){
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = layoutManager
            val adapterCast = CastAdapter(activity,cast)
            recyclerView.adapter = adapterCast
        }

        fun setProvider(txtView: TextView, recyclerView: RecyclerView, context: Context, activity: Activity, providers: List<DataProviders>){
            txtView.visibility = View.VISIBLE
            val layoutManagerProvider = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = layoutManagerProvider
            val adapterProvider = ProviderAdapter(activity, providers)
            recyclerView.adapter = adapterProvider
        }

        fun setTrailer(video: List<ResultVideo>, buton: ImageView, texto: TextView): Pair<Intent?,Intent?>{
            var idVideo = ""
            var appIntent: Intent? = null
            var webIntent: Intent? = null
            var flag = false
            if (video.isNotEmpty()){
                for (i in video.indices){
                    if (video[i].site == "YouTube"){
                        if (!flag) {
                            idVideo = video[i].key
                            flag = true
                        }
                    }
                }
                if (idVideo.isNotEmpty()){
                    buton.visibility = View.VISIBLE
                    texto.visibility = View.VISIBLE
                    appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$idVideo"))
                    webIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=$idVideo")
                    )
                }
            }
            return Pair(appIntent,webIntent)
        }
    }
}