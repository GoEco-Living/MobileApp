package com.ecoliving.mobile.data.remote.response

data class AddTransportResponse(
	val transport: Transport? = null,
	val message: String? = null
)

data class Transport(
	val predictedEmission: Any? = null,
	val distance: Int? = null,
	val type: String? = null,
	val userId: Int? = null
)

