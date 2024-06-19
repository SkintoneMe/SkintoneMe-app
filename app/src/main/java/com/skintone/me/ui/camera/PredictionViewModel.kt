package com.skintone.me.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skintone.me.data.response.DataSkin
import com.skintone.me.data.response.ModelResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class PredictionViewModel(private val predictionRepository: PredictionRepository): ViewModel() {

    private val _prediction = MutableLiveData<ModelResponse>()
    val prediction: LiveData<ModelResponse> = _prediction

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun predict(token: String, file: MultipartBody.Part) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = predictionRepository.preditc(token, file)
                _prediction.value = response
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

}