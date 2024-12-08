package com.ecoliving.mobile.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ecoliving.mobile.data.Result
import com.ecoliving.mobile.data.pref.UserModel
import com.ecoliving.mobile.data.remote.repository.DashboardRepository
import com.ecoliving.mobile.data.remote.response.DashboardResponse
import com.ecoliving.mobile.data.remote.response.MealsItem
import com.ecoliving.mobile.data.remote.response.MealsRecommResponse
import com.ecoliving.mobile.data.remote.response.TransportItem
import com.ecoliving.mobile.data.remote.response.TransportRecommResponse
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: DashboardRepository) : ViewModel() {
    private val _dashboardUser = MutableLiveData<Result<DashboardResponse>>()
    val dashboardUser: LiveData<Result<DashboardResponse>> get() = _dashboardUser

    private val _mealsRecommend = MutableLiveData<Result<MealsRecommResponse>>()
    val mealsRecommend: LiveData<Result<MealsRecommResponse>> get() = _mealsRecommend

    private val _transportRecommend = MutableLiveData<Result<TransportRecommResponse>>()
    val transportRecommend: LiveData<Result<TransportRecommResponse>> get() = _transportRecommend

    private val _listMeals = MutableLiveData<Result<List<MealsItem>>>()
    val listMeals: LiveData<Result<List<MealsItem>>> = _listMeals

    private val _listTransport = MutableLiveData<Result<List<TransportItem>>>()
    val listTransport: LiveData<Result<List<TransportItem>>> = _listTransport

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getTotalCarbon(id: Int) {
        _dashboardUser.value = Result.Loading
        viewModelScope.launch {
            val totalCarbon = repository.getTotalCarbon(id)
            _dashboardUser.value = totalCarbon
        }
    }

    fun getMealsRecommend(id: Int) {
        _mealsRecommend.value = Result.Loading
        viewModelScope.launch {
            val mealsRecommend = repository.getMealsRecommend(id)
            _mealsRecommend.value = mealsRecommend
        }
    }

    fun getMealsHistory(id: Int) {
        _listMeals.value = Result.Loading
        viewModelScope.launch {
            val mealsHistory = repository.getMealsHistory(id)
            _listMeals.value = mealsHistory
        }
    }

    fun getTransportHistory(id: Int) {
        _listTransport.value = Result.Loading
        viewModelScope.launch {
            val transportHistory = repository.getTransportHistory(id)
            _listTransport.value = transportHistory
        }
    }


    fun getTransportRecommend(id: Int) {
        _transportRecommend.value = Result.Loading
        viewModelScope.launch {
            val transportRecommend = repository.getTransportRecommend(id)
            _transportRecommend.value = transportRecommend
        }
    }
}