package com.example.movieapp.View.movies

import android.R.id
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.View.movies.adapter.CastAdapter
import com.example.movieapp.View.movies.adapter.MovieAdapter
import com.example.movieapp.View.movies.adapter.ProviderAdapter
import com.example.movieapp.api.ApiConstants
import com.example.movieapp.databinding.MoviesActivityBinding
import com.example.movieapp.db.DataBaseAdapter
import com.example.movieapp.db.DataDBHelper
import com.example.movieapp.domain.models.MovieModel
import com.example.movieapp.domain.response.*
import com.example.movieapp.expose.NavigationExpose
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.DialogMovie
import kotlinx.coroutines.launch


class MoviesActivity : AppCompatActivity(), MovieAdapter.OnItemMovieClickListener, MoviesInterface.MoviesView{

    private lateinit var binding: MoviesActivityBinding
    private var db: DataDBHelper? = null
    private var providers : ProvidersResponse? = null
    private var cast: CastResponse? = null
    private var videos: VideosResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MoviesActivityBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    fun initView(){
        db = DataDBHelper(this)
        setMovieView(Constants.TYPE_POPULAR,db!!,this,binding.popularRecycler,this,this,binding.morePopularTxt)
        setMovieView(Constants.TYPE_PLAYING,db!!,this,binding.playingRecycler,this,this,binding.morePlayingTxt)
        setMovieView(Constants.TYPE_UPCOMING,db!!,this,binding.upcomingRecycler,this,this,binding.moreUpcomingTxt)
        setMovieView(Constants.TYPE_TOP,db!!,this,binding.topRecycler,this,this,binding.moreTopTxt)
    }

    override fun onItemMovieClick(movie: MovieModel) {
        getDataMovie(movie)
    }

    private fun getDataMovie(movie: MovieModel){
        this.lifecycleScope.launch {
            try {
                videos = movie.id?.let {
                    NavigationExpose.getVideo(this@MoviesActivity,
                        it.toInt())
                }
                providers = movie.id?.let {
                    NavigationExpose.getProviders(this@MoviesActivity,
                        it.toInt())
                }
                cast = movie.id?.let {
                    NavigationExpose.getCast(this@MoviesActivity,
                        it.toInt())
                }
            }finally {
                if (providers == null && videos == null && cast == null){
                    setMovie(movie,null,null,null)
                }
                if (providers != null && videos != null && cast != null){
                    setMovie(movie,providers?.results?.MX?.flatrate,cast, videos!!.results)
                }
            }
        }
    }

    private fun setMovie(movie: MovieModel, providers: List<DataProviders>?, cast: CastResponse?, video: List<ResultVideo>?){
        val view = layoutInflater.inflate(R.layout.movie_popup_item, null)
        val dialog = Dialog(this, R.style.CustomBottomSheetDialog)
        dialog.setContentView(view)
        dialog.window!!.setGravity(Gravity.CENTER_VERTICAL)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT)
        DialogMovie.setMovieData(view,movie,providers,cast,video,this,this)
        dialog.show()
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}