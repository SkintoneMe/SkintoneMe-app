package com.skintone.me.favo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    companion object {
        const val PAGE_SIZE = 30
        const val PLACEHOLDER = true

        @Volatile
        private var INSTANCE: FavoriteRepository?= null

        fun getInstance(context: Context): FavoriteRepository {
            return INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    val database = FavoriteDatabase.getInstance(context)
                    INSTANCE = FavoriteRepository(database.favoriteDao())
                }
                return INSTANCE as FavoriteRepository
            }
        }

    }

    fun getALlFavorite(): LiveData<PagingData<FavoriteEntity>> {

        val pagingConfig = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = PLACEHOLDER
        )
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                favoriteDao.getAllFavorite()
            }
        ).liveData

    }

    suspend fun insertSkin(favoriteEntity: FavoriteEntity) {
        favoriteDao.inserSkin(favoriteEntity)
    }

}