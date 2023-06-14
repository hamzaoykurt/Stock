package com.example.duvarkagitlari.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.duvarkagitlari.CategoryActivity
import com.example.duvarkagitlari.R
import com.example.duvarkagitlari.adapter.CollectionsAdapter
import com.example.duvarkagitlari.databinding.FragmentCollecitonsBinding
import com.example.duvarkagitlari.databinding.FragmentFavoriteBinding
import com.example.duvarkagitlari.databinding.FragmentHomeBinding
import com.example.duvarkagitlari.model.Collections


class CollectionsFragment : Fragment() {

    private var _binding : FragmentCollecitonsBinding? = null
    private val binding get() = _binding!!

    private lateinit var collectionsAdapter: CollectionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollecitonsBinding.inflate(inflater,container,false)

        collectionsAdapter = CollectionsAdapter()


        val collectionList = arrayListOf<Collections>()

        collectionList.add(Collections(0,"Film",R.drawable.film_1))
        collectionList.add(Collections(1,"Hayvan",R.drawable.hayvan_1))
        collectionList.add(Collections(2,"İllüstrasyon",R.drawable.illustrasyon_1))
        collectionList.add(Collections(3,"Klasik",R.drawable.klasik_1))
        collectionList.add(Collections(4,"Manzara",R.drawable.manzara_1))
        collectionList.add(Collections(5,"Mistik",R.drawable.mistik_1))
        collectionList.add(Collections(6,"Şirin",R.drawable.sirin_1))

        binding.recyclerViewCollections.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = collectionsAdapter
        }

        collectionsAdapter.setCollectionsList(collectionList)

        collectionsAdapter.collectionOnClickListener = object : CollectionsAdapter.CollectionOnClickListener {
            override fun onClick(collection: Collections) {
                val intent = Intent(requireContext(),CategoryActivity::class.java)
                intent.putExtra("collectionName",collection.name)
                startActivity(intent)
            }

        }


        return binding.root
    }


}