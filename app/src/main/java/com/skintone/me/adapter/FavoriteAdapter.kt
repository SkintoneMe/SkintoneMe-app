package com.skintone.me.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skintone.me.databinding.ItemFavoriteBinding
import com.skintone.me.favo.FavoriteEntity

class FavoriteAdapter: PagingDataAdapter<FavoriteEntity, FavoriteAdapter.FavoriteViewHolder>(FavoriteDiffCallback) {

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FavoriteViewHolder(binding)
    }

    class FavoriteViewHolder(
        private val binding: ItemFavoriteBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoriteEntity) {
            binding.tvTextSkintone.text = item.skintone
        }
    }

    companion object {
        val FavoriteDiffCallback = object  : DiffUtil.ItemCallback<FavoriteEntity>() {
            override fun areItemsTheSame(
                oldItem: FavoriteEntity,
                newItem: FavoriteEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteEntity,
                newItem: FavoriteEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}