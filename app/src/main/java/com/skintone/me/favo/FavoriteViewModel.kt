package com.skintone.me.favo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository): ViewModel() {

    private val _favorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> = _favorite

    fun getAllFavorite(): LiveData<PagingData<FavoriteEntity>> {
        return favoriteRepository.getALlFavorite()
    }

    fun insertSkin(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            try {
                favoriteRepository.insertSkin(favoriteEntity)
                _favorite.value = true
            } catch (e: Exception) {
                _favorite.value = false
            }
        }
    }

}