package com.example.duvarkagitlari.db

import androidx.room.*
import com.example.duvarkagitlari.model.Wallpaper

@Dao
interface AppDao {


    @Query("SELECT * FROM wallpaper WHERE isFavorite=1")
    fun getFavorite(): List<Wallpaper>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(vararg wallpaper: Wallpaper)

    @Delete
    fun removeFavorite(wallpaper: Wallpaper)


}