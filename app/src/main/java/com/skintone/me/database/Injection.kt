package com.skintone.me.database

import android.content.Context
import com.skintone.me.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val pref = PreferenceManager.getInstance(context.dataStore)
        return UserRepository.getInstance(pref, apiService)
    }
}