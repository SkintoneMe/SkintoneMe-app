package com.skintone.me.data.response

import com.google.gson.annotations.SerializedName

data class ModelResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("predictedClassIndex")
	val predictedClassIndex: Int? = null,

	@field:SerializedName("jewelryRecommendation")
	val jewelryRecommendation: List<String?>? = null,

	@field:SerializedName("recommendation")
	val recommendation: List<String?>? = null,

	@field:SerializedName("predictedClassName")
	val predictedClassName: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("predictions")
	val predictions: Predictions? = null
)

data class Predictions(

	@field:SerializedName("0")
	val jsonMember0: Any? = null,

	@field:SerializedName("1")
	val jsonMember1: Any? = null,

	@field:SerializedName("2")
	val jsonMember2: Any? = null,

	@field:SerializedName("3")
	val jsonMember3: Any? = null
)
