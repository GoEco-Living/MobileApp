package com.ecoliving.mobile.data.remote.repository

import com.ecoliving.mobile.data.pref.UserModel
import com.ecoliving.mobile.data.pref.UserPreference
import com.ecoliving.mobile.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

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

//    companion object {
//        @Volatile
//        private var instance: DashboardRepository? = null
//        fun getInstance(
//            apiService: ApiService,
//            userPreference: UserPreference
//        ): DashboardRepository =
//            instance ?: synchronized(this) {
//                instance ?: DashboardRepository(userPreference, apiService)
//            }.also { instance = it }
//    }

    companion object {
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ) = DashboardRepository(userPreference, apiService)
    }
}