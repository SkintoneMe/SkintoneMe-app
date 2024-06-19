package com.skintone.me.favo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoriteFactory(private val favoriteRepository: FavoriteRepository): ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var instance: FavoriteFactory? = null

        fun getInstance(context: Context): FavoriteFactory =
            instance ?: synchronized(this) {
                instance ?: FavoriteFactory(
                    FavoriteRepository.getInstance(context)
                )
            }

    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(favoriteRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}