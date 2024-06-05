package com.skintone.me.database.api

import com.skintone.me.response.LoginResponse
import com.skintone.me.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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
        @Field("name") name: String,
        @Field("gender") gender: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

}