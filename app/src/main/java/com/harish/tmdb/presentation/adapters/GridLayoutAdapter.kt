package com.harish.tmdb.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harish.tmdb.app.GlideApp
import com.harish.tmdb.data.remote.modals.PopularMoviesList
import com.harish.tmdb.databinding.PrimaryCardLayoutBinding
import com.harish.tmdb.databinding.PrimaryCardLayoutBinding.inflate

class GridLayoutAdapter(private val list: PopularMoviesList) :
    RecyclerView.Adapter<GridLayoutAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val context: Context, private val binder: PrimaryCardLayoutBinding
    ) : RecyclerView.ViewHolder(binder.root) {
        fun onBind(item: PopularMoviesList.Result) {
            binder.primaryPosterIcon.clipToOutline = true
            GlideApp.with(context)
                .load("https://image.tmdb.org/t/p/w500/${item.posterPath.drop(1)}")
                .into(binder.primaryPosterIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.context, inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int = list.results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list.results[position])
    }
}
