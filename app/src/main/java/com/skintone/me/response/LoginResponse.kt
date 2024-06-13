package com.skintone.me.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
	val gender: String? = null,
	val data: Data? = null,
	val message: String? = null,
	val email: String? = null,
	val status: Boolean? = null,
	val username: String? = null
)

data class Data(

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null

)

