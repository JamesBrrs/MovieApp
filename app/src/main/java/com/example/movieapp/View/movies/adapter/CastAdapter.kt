package com.example.movieapp.View.movies.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.api.ApiConstants
import com.example.movieapp.databinding.CastItemBinding
import com.example.movieapp.domain.response.Cast

class CastAdapter(private val activity: Activity, private val cast: List<Cast>): RecyclerView.Adapter<CastAdapter.CastHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.cast_item, parent, false)
        return CastHolder(view)
    }

    override fun onBindViewHolder(holder: CastHolder, position: Int) {
        holder.renderCast(activity,cast[position])
    }

    override fun getItemCount(): Int {
        return cast.size
    }

    inner class CastHolder(val view: View): RecyclerView.ViewHolder(view){
        fun renderCast(activity: Activity, cast: Cast){
            val binding = CastItemBinding.bind(view)
            val image = ApiConstants.BASE_IMAGE_URL+cast.profile_path
            Glide.with(activity).load(image).into(binding.imageCast)
            binding.castName.text = cast.name
        }
    }
}