package com.example.duvarkagitlari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.duvarkagitlari.databinding.ActivityMainBinding
import com.example.duvarkagitlari.databinding.LayoutMenuBinding
import com.example.duvarkagitlari.fragments.CollectionsFragment
import com.example.duvarkagitlari.fragments.FavoriteFragment
import com.example.duvarkagitlari.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var selectedFragment = "home"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment = HomeFragment()

        showFragment(homeFragment)

        binding.imageViewMenu.setOnClickListener {
            openMenu()
        }


    }

    private fun openMenu() {
        val alertDialog = AlertDialog.Builder(this,R.style.DialogTheme)
        val alert = alertDialog.create()

        val binding = LayoutMenuBinding.inflate(LayoutInflater.from(this),null,false)

        alert.setView(binding.root)

        if (selectedFragment.isNotEmpty()) {
            when(selectedFragment) {
                "home" -> {
                    binding.textViewHome.setBackgroundColor(ContextCompat.getColor(this,R.color.color_gray))
                    binding.textViewHome.setTextColor(ContextCompat.getColor(this,R.color.white))
                }
                "collections" -> {
                    binding.textViewCollections.setBackgroundColor(ContextCompat.getColor(this,R.color.color_gray))
                    binding.textViewCollections.setTextColor(ContextCompat.getColor(this,R.color.white))
                }
                "favorite" -> {
                    binding.textViewFavorite.setBackgroundColor(ContextCompat.getColor(this,R.color.color_gray))
                    binding.textViewFavorite.setTextColor(ContextCompat.getColor(this,R.color.white))
                }
            }
        }

        binding.imageViewClose.setOnClickListener { alert.dismiss() }

        val homeFragment = HomeFragment()
        val collectionsFragment = CollectionsFragment()
        val favoriteFragment = FavoriteFragment()

        binding.textViewHome.setOnClickListener {
            showFragment(homeFragment)
            alert.dismiss()
            selectedFragment = "home"
        }

        binding.textViewCollections.setOnClickListener {
            showFragment(collectionsFragment)
            alert.dismiss()
            selectedFragment = "collections"
        }

        binding.textViewFavorite.setOnClickListener {
            showFragment(favoriteFragment)
            alert.dismiss()
            selectedFragment = "favorite"
        }



        alert.show()


    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.frameLayout.id,fragment).commit()
    }

}