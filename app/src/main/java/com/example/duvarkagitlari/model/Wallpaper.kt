package com.example.duvarkagitlari.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Wallpaper(
    @PrimaryKey
    val id: Int,
    val image: Int,
    var isFavorite: Boolean = false
) : Parcelable{
}