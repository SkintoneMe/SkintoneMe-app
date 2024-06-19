package com.skintone.me.ui.camera

import com.skintone.me.data.response.DataSkin
import com.skintone.me.data.response.ModelResponse
import com.skintone.me.data.retrofit.ApiService
import okhttp3.MultipartBody

class PredictionRepository(private val apiService: ApiService) {

    suspend fun preditc(file: MultipartBody.Part): ModelResponse {
        return apiService.predict(file)
    }

    companion object {
        @Volatile
        private var instance: PredictionRepository? = null

        fun getInstance(apiService: ApiService): PredictionRepository =
            instance ?: synchronized(this) {
                instance ?: PredictionRepository(apiService)
            }.also { instance = it }

    }

}