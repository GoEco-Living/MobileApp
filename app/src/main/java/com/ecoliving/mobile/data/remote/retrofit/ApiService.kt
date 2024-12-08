package com.ecoliving.mobile.data.remote.retrofit

import com.ecoliving.mobile.data.remote.repository.LoginRequest
import com.ecoliving.mobile.data.remote.repository.MealsRequest
import com.ecoliving.mobile.data.remote.repository.RegisterRequest
import com.ecoliving.mobile.data.remote.response.DashboardResponse
import com.ecoliving.mobile.data.remote.response.LoginResponse
import com.ecoliving.mobile.data.remote.response.MealsRecommResponse
import com.ecoliving.mobile.data.remote.response.MealsResponse
import com.ecoliving.mobile.data.remote.response.RegisterResponse
import com.ecoliving.mobile.data.remote.response.TransportRecommResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("meals")
    suspend fun meals(
        @Body request: MealsRequest
    ): MealsResponse

    @GET("user/{id}/dashboard")
    suspend fun getDashboardUser(
        @Path("id") id: Int
    ): DashboardResponse

    @GET("user/{id}/meal_recommendation")
    suspend fun getMealsRecommend(
        @Path("id") id: Int
    ): MealsRecommResponse

    @GET("user/{id}/transport_recommendation")
    suspend fun getTransportRecommend(
        @Path("id") id: Int
    ): TransportRecommResponse
}