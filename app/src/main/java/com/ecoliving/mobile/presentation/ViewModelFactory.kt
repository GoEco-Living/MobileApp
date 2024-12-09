package com.ecoliving.mobile.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ecoliving.mobile.data.datainject.Injection
import com.ecoliving.mobile.data.remote.repository.DashboardRepository
import com.ecoliving.mobile.data.remote.repository.TransportRepository
import com.ecoliving.mobile.data.remote.repository.UserRepository
import com.ecoliving.mobile.presentation.ui.activity.transport.TransportViewModel
import com.ecoliving.mobile.presentation.ui.dashboard.DashboardViewModel
import com.ecoliving.mobile.presentation.ui.login.LoginViewModel
import com.ecoliving.mobile.presentation.ui.profile.ProfileViewModel
import com.ecoliving.mobile.presentation.ui.register.RegisterViewModel

class ViewModelFactory private constructor(
    private val userRepository: UserRepository,
    private val dashboardRepository: DashboardRepository,
    private val transportRepository: TransportRepository
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> LoginViewModel(userRepository) as T
            RegisterViewModel::class.java -> RegisterViewModel(userRepository) as T
            DashboardViewModel::class.java -> DashboardViewModel(dashboardRepository) as T
            ProfileViewModel::class.java -> ProfileViewModel(dashboardRepository) as T
            TransportViewModel::class.java -> TransportViewModel(transportRepository) as T
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        fun getInstance(context: Context) =
            ViewModelFactory(
                Injection.provideUserRepository(context),
                Injection.provideDashboardRepository(context),
                Injection.provideTransportRepository(context)
            )
    }
}