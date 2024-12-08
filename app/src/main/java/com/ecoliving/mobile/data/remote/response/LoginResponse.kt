package com.ecoliving.mobile.data.remote.response

data class LoginResponse(
	val name: String? = null,
	val userId: Int,
	val email: String? = null,
	val token: String? = null
)

