package com.example.duvarkagitlari.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.duvarkagitlari.R
import com.example.duvarkagitlari.WallpaperDetailActivity
import com.example.duvarkagitlari.adapter.WallpaperAdapter
import com.example.duvarkagitlari.databinding.FragmentFavoriteBinding
import com.example.duvarkagitlari.databinding.FragmentHomeBinding
import com.example.duvarkagitlari.db.AppDatabase
import com.example.duvarkagitlari.model.Wallpaper


class FavoriteFragment : Fragment() {

    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var wallpaperAdapter: WallpaperAdapter
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)

        wallpaperAdapter = WallpaperAdapter()
        appDatabase = AppDatabase(requireContext())


        binding.recyclerViewFavorite.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = wallpaperAdapter
        }

        wallpaperAdapter.setWallpaperList(appDatabase.appDao().getFavorite())

        wallpaperAdapter.wallpaperOnClickListener =
            object : WallpaperAdapter.WallpaperOnClickListener {
                override fun onClick(wallpaper: Wallpaper) {
                    val intent = Intent(requireContext(), WallpaperDetailActivity::class.java)
                    intent.putExtra("wallpaper", wallpaper)
                    startActivity(intent)
                }

                override fun favoriteClick(wallpaper: Wallpaper, imageView: ImageView) {
                    val fav = appDatabase.appDao().getFavorite().find { it == wallpaper }
                    if (fav == null) {
                        wallpaper.isFavorite = true
                        appDatabase.appDao().addFavorite(wallpaper)
                        imageView.setBackgroundResource(R.drawable.baseline_favorite_24)
                    } else {
                        wallpaper.isFavorite = true
                        appDatabase.appDao().removeFavorite(wallpaper)
                        imageView.setBackgroundResource(R.drawable.ic_favorite_border_24)
                    }
                }

            }


        return binding.root
    }


}