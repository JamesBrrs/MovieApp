package com.example.movieapp.View.more

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.R
import com.example.movieapp.View.more.adapter.MoreAdapter
import com.example.movieapp.databinding.MoreMoviesActivityBinding
import com.example.movieapp.databinding.MoviesActivityBinding
import com.example.movieapp.db.DataBaseAdapter
import com.example.movieapp.db.DataDBHelper
import com.example.movieapp.domain.models.MovieModel
import com.example.movieapp.domain.response.*
import com.example.movieapp.expose.NavigationExpose
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.DialogMovie
import kotlinx.coroutines.launch

class MoreMoviesActivity : AppCompatActivity(), MoreMoviesInterface.MoreView, MoreAdapter.OnItemMovieMoreClickListener{

    private lateinit var binding: MoreMoviesActivityBinding
    private var type = ""
    private var providers : ProvidersResponse? = null
    private var cast: CastResponse? = null
    private var videos: VideosResponse? = null
    private var db: DataDBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MoreMoviesActivityBinding.inflate(layoutInflater)
        getExtras()
        setContentView(binding.root)
    }

    private fun getExtras() {
        db = DataDBHelper(this)
        type = intent.extras?.getString("TYPE").toString()
        setView(db!!, type, this, this, binding.moreRecycler, this,lifecycleScope)
    }
    override fun onItemMovieMoreClick(movie: MovieModel) {
        getDataMovie(movie)
    }

    private fun getDataMovie(movie: MovieModel){
        this.lifecycleScope.launch {
            try {
                videos = movie.id?.let {
                    NavigationExpose.getVideo(this@MoreMoviesActivity,
                        it.toInt())
                }
                providers = movie.id?.let {
                    NavigationExpose.getProviders(this@MoreMoviesActivity,
                        it.toInt())
                }
                cast = movie.id?.let {
                    NavigationExpose.getCast(this@MoreMoviesActivity,
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

}