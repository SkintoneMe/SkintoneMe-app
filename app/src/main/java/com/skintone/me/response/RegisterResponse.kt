package com.skintone.me.response

import com.google.gson.annotations.SerializedName

class RegisterResponse (

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)
