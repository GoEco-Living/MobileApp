package com.ecoliving.mobile.data.remote.response

data class MealsResponse(
	val meal: Meal? = null,
	val message: String? = null
)

data class Meal(
	val predictedEmission: Any? = null,
	val type: String? = null,
	val userId: Int? = null
)

