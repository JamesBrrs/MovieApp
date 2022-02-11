package com.example.movieapp.View.movies.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.api.ApiConstants
import com.example.movieapp.databinding.ProviderItemBinding
import com.example.movieapp.domain.response.DataProviders

class ProviderAdapter (private val activity: Activity, private val provider: List<DataProviders>): RecyclerView.Adapter<ProviderAdapter.ProviderHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.provider_item, parent, false)
        return ProviderHolder(view)
    }

    override fun onBindViewHolder(holder: ProviderHolder, position: Int) {
        holder.renderCast(activity,provider[position])
    }

    override fun getItemCount(): Int {
        return provider.size
    }

    inner class ProviderHolder(val view: View): RecyclerView.ViewHolder(view){
        fun renderCast(activity: Activity, provider: DataProviders){
            val binding = ProviderItemBinding.bind(view)
            val image = ApiConstants.BASE_IMAGE_URL+provider.logo_path
            Glide.with(activity).load(image).into(binding.imageProvider)
        }
    }
}