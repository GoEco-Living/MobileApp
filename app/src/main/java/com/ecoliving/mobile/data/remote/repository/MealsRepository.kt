package com.ecoliving.mobile.data.remote.repository

import com.ecoliving.mobile.data.remote.retrofit.ApiService
import com.ecoliving.mobile.data.remote.response.MealsResponse
import com.ecoliving.mobile.data.pref.UserPreference
import kotlinx.coroutines.flow.first

data class MealsRequest(
    val type: String,
    val userId: Int,
    val predictedEmission: Boolean
)

class MealsRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {
    suspend fun mealsUser(type: String, predictedEmission: Boolean): MealsResponse {
        val userId = userPreference.getSession().first().userId

        val request = MealsRequest(
            type = type,
            userId = userId,
            predictedEmission = predictedEmission
        )
        return apiService.meals(request)
    }

    companion object {
        @Volatile
        private var INSTANCE: MealsRepository? = null

        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): MealsRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = MealsRepository(apiService, userPreference)
                INSTANCE = instance
                instance
            }
        }
    }
}
