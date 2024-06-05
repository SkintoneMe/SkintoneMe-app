package com.skintone.me.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.skintone.me.database.api.ApiService
import com.skintone.me.response.RegisterResponse


class UserRepository (private val preferenceManager: PreferenceManager, private val apiService: ApiService) {

    suspend fun login(email: String, password: String) =
        apiService.login(email, password)

    suspend fun register(name: String, gender: String, email: String, password: String) : RegisterResponse {
        Log.d("Register", "$name, $gender, $email, $password")
        return apiService.register(name, gender, email, password)
    }


    fun getSession() : LiveData<User> {
        return preferenceManager.getSession().asLiveData()
    }


    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: PreferenceManager,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }

}