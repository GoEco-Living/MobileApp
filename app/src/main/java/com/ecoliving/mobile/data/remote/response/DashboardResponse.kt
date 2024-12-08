package com.ecoliving.mobile.data.remote.response

data class DashboardResponse(
	val totalCarbonEmission: String? = null,
	val transport: List<TransportItem> = listOf(),
	val userId: String? = null,
	val meals: List<MealsItem> = listOf()
)

data class TransportItem(
	val distance: Int? = null,
	val carbonEmission: String? = null,
	val type: String? = null
)

data class MealsItem(
	val carbonEmission: String? = null,
	val type: String? = null
)

