package com.ecoliving.mobile.data.remote.repository

import com.ecoliving.mobile.data.pref.UserModel
import com.ecoliving.mobile.data.pref.UserPreference
import com.ecoliving.mobile.data.remote.response.DashboardResponse
import com.ecoliving.mobile.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import com.ecoliving.mobile.data.Result
import com.ecoliving.mobile.data.remote.response.MealsItem
import com.ecoliving.mobile.data.remote.response.MealsRecommResponse
import com.ecoliving.mobile.data.remote.response.TransportItem
import com.ecoliving.mobile.data.remote.response.TransportRecommResponse


class DashboardRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
) {

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun getTotalCarbon(id: Int): Result<DashboardResponse> {
        return try {
            val response = apiService.getDashboardUser(id)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error("Exception: ${e.message}")
        }
    }

    suspend fun getMealsHistory(id: Int): Result<List<MealsItem>> {
        return try {
            val response = apiService.getDashboardUser(id)
            val mealsList = response.meals
            Result.Success(mealsList)
        } catch (e: Exception) {
            Result.Error("Exception: ${e.message}")
        }
    }

    suspend fun getTransportHistory(id: Int): Result<List<TransportItem>> {
        return try {
            val response = apiService.getDashboardUser(id)
            val mealsList = response.transport
            Result.Success(mealsList)
        } catch (e: Exception) {
            Result.Error("Exception: ${e.message}")
        }
    }

    suspend fun getMealsRecommend(id: Int): Result<MealsRecommResponse> {
        return try {
            val response = apiService.getMealsRecommend(id)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error("Exception: ${e.message}")
        }
    }

    suspend fun getTransportRecommend(id: Int): Result<TransportRecommResponse> {
        return try {
            val response = apiService.getTransportRecommend(id)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error("Exception: ${e.message}")
        }
    }

    companion object {
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ) = DashboardRepository(userPreference, apiService)
    }
}