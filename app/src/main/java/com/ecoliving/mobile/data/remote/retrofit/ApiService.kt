package com.ecoliving.mobile.data.remote.retrofit

import com.ecoliving.mobile.data.remote.repository.LoginRequest
import com.ecoliving.mobile.data.remote.repository.RegisterRequest
import com.ecoliving.mobile.data.remote.response.LoginResponse
import com.ecoliving.mobile.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}