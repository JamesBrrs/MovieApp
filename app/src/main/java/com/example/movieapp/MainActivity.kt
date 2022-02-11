package com.example.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.View.splash.SplashActivityView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSplash()
    }

    private fun initSplash(){
        navigateToActivity(SplashActivityView::class.java)
    }

    fun navigateToActivity(activity: Class<*>){
        startActivity(Intent(this,activity))
    }

}