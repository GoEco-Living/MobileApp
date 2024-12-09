package com.ecoliving.mobile.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ecoliving.mobile.data.pref.UserModel
import com.ecoliving.mobile.data.remote.repository.DashboardRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: DashboardRepository) : ViewModel() {
    private val _logoutStatus = MutableLiveData<Boolean>()
    val logoutStatus: LiveData<Boolean> get() = _logoutStatus

    fun logout() {
        viewModelScope.launch {
            try {
                repository.logout()
                _logoutStatus.value = true
            } catch (e: Exception) {
                _logoutStatus.value = false
            }
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}