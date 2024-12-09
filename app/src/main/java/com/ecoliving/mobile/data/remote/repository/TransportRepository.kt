package com.ecoliving.mobile.data.remote.repository

import com.ecoliving.mobile.data.Result
import com.ecoliving.mobile.data.pref.UserModel
import com.ecoliving.mobile.data.pref.UserPreference
import com.ecoliving.mobile.data.remote.response.AddTransportResponse
import com.ecoliving.mobile.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

data class TransportRequest(
    val userId: Int,
    val type: String,
    val distance: Int
)

class TransportRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
) {

    suspend fun addTransportActivity(
        userId: Int,
        type: String,
        distance: Int
    ): Result<AddTransportResponse> {
        return try {
            val addTransportReq = TransportRequest(userId, type, distance)
            val response = apiService.addTransportActivity(addTransportReq)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error("Failed add activity")
        }
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    companion object {
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ) = TransportRepository(userPreference, apiService)
    }
}