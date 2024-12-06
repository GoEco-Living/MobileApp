package com.ecoliving.mobile.data.datainject

import android.content.Context
import com.ecoliving.mobile.data.pref.UserPreference
import com.ecoliving.mobile.data.pref.dataStore
import com.ecoliving.mobile.data.remote.repository.DashboardRepository
import com.ecoliving.mobile.data.remote.repository.UserRepository
import com.ecoliving.mobile.data.remote.retrofit.ApiConfig
import com.example.ecoliving.BuildConfig

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService ,pref)
    }

    fun provideDashboardRepository(context: Context): DashboardRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return DashboardRepository.getInstance(pref ,apiService)
    }
}