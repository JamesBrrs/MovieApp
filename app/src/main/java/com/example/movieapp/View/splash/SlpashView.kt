package com.example.movieapp.View.splash

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
import android.window.SplashScreen
import com.airbnb.lottie.LottieAnimationView
import com.example.movieapp.db.DataBaseAdapter
import com.example.movieapp.db.DataDBHelper
import com.example.movieapp.domain.models.MovieModel
import com.example.movieapp.domain.response.BaseMovieResponse
import com.example.movieapp.utils.Constants

interface SlpashView {

    interface Animate{

        fun animateLottie(animation: LottieAnimationView, anim: Int){
            animation.bringToFront()
            animation.setAnimation(anim)
            animation.playAnimation()
        }
    }

    interface Movies{

        fun setMovies(db: DataDBHelper,context: Context, popular: BaseMovieResponse?, playing: BaseMovieResponse?,
                      upcoming: BaseMovieResponse?, top: BaseMovieResponse?){
            if (popular != null){
                DataBaseAdapter.addMovies(db,context,Constants.TYPE_POPULAR,popular)
            }
            if (playing != null){
                DataBaseAdapter.addMovies(db,context,Constants.TYPE_PLAYING,playing)
            }
            if (upcoming != null){
                DataBaseAdapter.addMovies(db,context,Constants.TYPE_UPCOMING,upcoming)
            }
            if (top != null){
                DataBaseAdapter.addMovies(db,context,Constants.TYPE_TOP,top)
            }
        }
    }

}