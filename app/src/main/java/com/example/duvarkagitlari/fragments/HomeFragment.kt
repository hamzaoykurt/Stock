package com.example.duvarkagitlari.fragments

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
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
import com.example.duvarkagitlari.databinding.FragmentHomeBinding
import com.example.duvarkagitlari.db.AppDatabase
import com.example.duvarkagitlari.model.Wallpaper


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeWallpaperAdapter: WallpaperAdapter

    private lateinit var appDatabase: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        appDatabase = AppDatabase(requireContext())
        homeWallpaperAdapter = WallpaperAdapter()

        val wallpaperList = arrayListOf<Wallpaper>()
        wallpaperList.add(Wallpaper(0, R.drawable.as_1))
        wallpaperList.add(Wallpaper(1, R.drawable.as_2))
        wallpaperList.add(Wallpaper(2, R.drawable.as_3))
        wallpaperList.add(Wallpaper(3, R.drawable.as_4))
        wallpaperList.add(Wallpaper(4, R.drawable.as_5))
        wallpaperList.add(Wallpaper(5, R.drawable.as_6))
        wallpaperList.add(Wallpaper(6, R.drawable.as_7))
        wallpaperList.add(Wallpaper(7, R.drawable.as_8))
        wallpaperList.add(Wallpaper(8, R.drawable.as_9))
        wallpaperList.add(Wallpaper(9, R.drawable.as_10))
        wallpaperList.add(Wallpaper(10, R.drawable.as_11))
        wallpaperList.add(Wallpaper(11, R.drawable.as_12))
        wallpaperList.add(Wallpaper(12, R.drawable.as_13))
        wallpaperList.add(Wallpaper(13, R.drawable.as_14))
        wallpaperList.add(Wallpaper(14, R.drawable.as_15))
        wallpaperList.add(Wallpaper(15, R.drawable.as_16))
        wallpaperList.add(Wallpaper(16, R.drawable.as_17))
        wallpaperList.add(Wallpaper(17, R.drawable.as_18))
        wallpaperList.add(Wallpaper(18, R.drawable.as_19))

        binding.recyclerViewHome.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = homeWallpaperAdapter
        }

        homeWallpaperAdapter.setWallpaperList(wallpaperList)

        homeWallpaperAdapter.wallpaperOnClickListener =
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