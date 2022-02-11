package com.example.movieapp.View.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.R
import com.example.movieapp.View.movies.MoviesActivity
import com.example.movieapp.databinding.SplashViewActivityBinding
import com.example.movieapp.db.DataBaseAdapter
import com.example.movieapp.db.DataDBHelper
import com.example.movieapp.expose.NavigationExpose
import kotlinx.coroutines.launch

class SplashActivityView : AppCompatActivity(), SlpashView.Animate, SlpashView.Movies  {

    private lateinit var binding: SplashViewActivityBinding
    private var db: DataDBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashViewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animateLottie(binding.movieAnimation,R.raw.movie)
        initDataBase()
        getFirstPages()
    }

    private fun initDataBase(){
        db = DataDBHelper(this)
    }


    fun navigateToActivity(activity: Class<*>){
        startActivity(Intent(this,activity))
    }

    private fun getFirstPages(){
        for (i in 1..10){
            getData(i)
        }
    }

    private fun getData(page: Int){
        lifecycleScope.launch {
            try {
                val dataPopular = NavigationExpose.getMostPopular(this@SplashActivityView,page)
                val dataPlayingNow = NavigationExpose.getPlayingNow(this@SplashActivityView,page)
                val dataUpComing = NavigationExpose.getUpcoming(this@SplashActivityView,page)
                val dataTop = NavigationExpose.getTop(this@SplashActivityView,page)
                if (page == 1 && dataPopular != null && dataPlayingNow != null && dataUpComing != null && dataTop != null){
                    db?.cleanDB()
                }
                db?.let { setMovies(it,this@SplashActivityView,dataPopular,dataPlayingNow,dataUpComing,dataTop) }
            } finally {
                if (page == 4)
                    navigateToActivity(MoviesActivity::class.java)
            }
        }
    }

}