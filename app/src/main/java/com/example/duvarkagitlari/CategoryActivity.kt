package com.example.duvarkagitlari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.duvarkagitlari.adapter.WallpaperAdapter
import com.example.duvarkagitlari.databinding.ActivityCategoryBinding
import com.example.duvarkagitlari.db.AppDatabase
import com.example.duvarkagitlari.model.Wallpaper

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    private lateinit var homeWallpaperAdapter: WallpaperAdapter

    var wallpaperList = listOf<Wallpaper>()

    private lateinit var appDatabase : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase(this)

        val collectionName = intent.getStringExtra("collectionName")

        binding.textViewCategoryName.text = collectionName

        homeWallpaperAdapter = WallpaperAdapter()

        when (collectionName) {
            "Film" -> wallpaperList = filmWallpaperList()
            "Hayvan" -> wallpaperList = hayvanWallpaperList()
            "İllüstrasyon" -> wallpaperList = illustrasyonWallpaperList()
            "Klasik" -> wallpaperList = klasikWallpaperList()
            "Manzara" -> wallpaperList = manzaraWallpaperList()
            "Mistik" -> wallpaperList = mistikWallpaperList()
            "Şirin" -> wallpaperList = sirinWallpaperList()
        }

        binding.recyclerViewWallpapers.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = homeWallpaperAdapter
        }

        homeWallpaperAdapter.setWallpaperList(wallpaperList)

        homeWallpaperAdapter.wallpaperOnClickListener =
            object : WallpaperAdapter.WallpaperOnClickListener {
                override fun onClick(wallpaper: Wallpaper) {
                    val intent = Intent(this@CategoryActivity,WallpaperDetailActivity::class.java)
                    intent.putExtra("wallpaper",wallpaper)
                    startActivity(intent)
                }

                override fun favoriteClick(wallpaper: Wallpaper, imageView: ImageView) {

                }

            }

        homeWallpaperAdapter.wallpaperOnClickListener =
            object : WallpaperAdapter.WallpaperOnClickListener {
                override fun onClick(wallpaper: Wallpaper) {
                    val intent = Intent(this@CategoryActivity, WallpaperDetailActivity::class.java)
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


    }
}