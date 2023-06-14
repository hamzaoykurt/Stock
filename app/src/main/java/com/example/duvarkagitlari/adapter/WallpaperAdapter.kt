package com.example.duvarkagitlari.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.duvarkagitlari.R
import com.example.duvarkagitlari.databinding.WallpaperViewHolderBinding
import com.example.duvarkagitlari.db.AppDatabase
import com.example.duvarkagitlari.model.Wallpaper

class WallpaperAdapter : RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {

    private val wallpaperList = arrayListOf<Wallpaper>()

    private lateinit var appDatabase: AppDatabase

    var wallpaperOnClickListener: WallpaperOnClickListener? = null

    fun setWallpaperList(list: List<Wallpaper>) {
        wallpaperList.clear()
        wallpaperList.addAll(list)
        notifyDataSetChanged()
    }


    class WallpaperViewHolder(val binding: WallpaperViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(wallpaper: Wallpaper) {
            binding.imageViewWallpaper.setImageResource(wallpaper.image)
        }

    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        holder.bind(wallpaperList[position])

        appDatabase = AppDatabase(holder.itemView.context)

        appDatabase.appDao().getFavorite().forEach {
            if (it.image == wallpaperList[position].image) {
                holder.binding.imageViewFavorite.tag = "favorite"
                holder.binding.imageViewFavorite.setBackgroundResource(R.drawable.baseline_favorite_24)
            }
        }



        holder.itemView.setOnClickListener {
            wallpaperOnClickListener?.onClick(wallpaperList[position])
        }

        holder.binding.layoutFavorite.setOnClickListener {
            wallpaperOnClickListener?.favoriteClick(wallpaperList[position],holder.binding.imageViewFavorite)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        return WallpaperViewHolder(
            WallpaperViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return wallpaperList.size
    }

    interface WallpaperOnClickListener {
        fun onClick(wallpaper: Wallpaper)
        fun favoriteClick(wallpaper: Wallpaper,imageView: ImageView)
    }

}