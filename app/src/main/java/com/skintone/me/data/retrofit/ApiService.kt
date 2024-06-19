package com.skintone.me.data.retrofit

import com.skintone.me.data.response.LoginResponse
import com.skintone.me.data.response.ModelResponse
import com.skintone.me.data.response.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("gender") gender: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @Multipart
    @POST("predict")
    suspend fun predict(
//        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ) : ModelResponse


}