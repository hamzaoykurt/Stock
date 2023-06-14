package com.example.duvarkagitlari.db

import android.content.Context
import androidx.room.*
import com.example.duvarkagitlari.model.Wallpaper


@Database(entities = [Wallpaper::class], version = 2)

abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "wallpaper_db"
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

}