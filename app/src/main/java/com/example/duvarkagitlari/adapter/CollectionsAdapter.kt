package com.example.duvarkagitlari.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.duvarkagitlari.databinding.CollectionsViewHolderBinding
import com.example.duvarkagitlari.model.Collections
import com.example.duvarkagitlari.model.Wallpaper

class CollectionsAdapter : RecyclerView.Adapter<CollectionsAdapter.CollectionViewHolder>() {

    private val collectionList = arrayListOf<Collections>()

    var collectionOnClickListener: CollectionOnClickListener? = null

    fun setCollectionsList(list: List<Collections>) {
        collectionList.clear()
        collectionList.addAll(list)
        notifyDataSetChanged()
    }


    class CollectionViewHolder(val binding: CollectionsViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(collection: Collections) {
            binding.imageViewWallpaper.setImageResource(collection.image)
            binding.textViewCollectionName.text = collection.name
        }

    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.bind(collectionList[position])

        holder.itemView.setOnClickListener {
            collectionOnClickListener?.onClick(collectionList[position])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder(
            CollectionsViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return collectionList.size
    }

    interface CollectionOnClickListener {
        fun onClick(collection: Collections)
    }

}