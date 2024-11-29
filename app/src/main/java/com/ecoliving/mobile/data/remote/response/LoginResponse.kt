package com.ecoliving.mobile.data.remote.response

data class LoginResponse(
	val name: String? = null,
	val userId: Int? = null,
	val email: String? = null,
	val token: String? = null
)

