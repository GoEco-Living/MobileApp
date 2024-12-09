package com.ecoliving.mobile.data.remote.repository

import com.ecoliving.mobile.data.Result
import com.ecoliving.mobile.data.pref.UserModel
import com.ecoliving.mobile.data.remote.retrofit.ApiService
import com.ecoliving.mobile.data.remote.response.MealsResponse
import com.ecoliving.mobile.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow

data class MealsRequest(
    val userId: Int,
    val type: String,
    val predictedEmission: Boolean
)

class MealsRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {
    suspend fun addMealsActivity(
        userId: Int,
        type: String,
        predictedEmission: Boolean
    ): Result<MealsResponse> {
        return try {
            val mealsUser = MealsRequest(userId, type, predictedEmission)
            val response = apiService.addMealsActivity(mealsUser)
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
            apiService: ApiService,
            userPreference: UserPreference
        ) = MealsRepository(apiService, userPreference)
    }
}