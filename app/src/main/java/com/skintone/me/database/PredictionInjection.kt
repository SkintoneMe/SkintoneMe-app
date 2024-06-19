package com.skintone.me.database

import android.content.Context
import com.skintone.me.data.retrofit.ApiConfig
import com.skintone.me.ui.camera.PredictionRepository

object PredictionInjection {
    fun provideRepository(context: Context): PredictionRepository {
        val apiService = ApiConfig.getApiService()
        return PredictionRepository.getInstance(apiService)
    }
}