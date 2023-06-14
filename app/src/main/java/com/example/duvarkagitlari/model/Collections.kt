package com.example.duvarkagitlari.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Collections(
    val id: Int,
    val name: String,
    val image: Int
): Parcelable {
}