package com.ecoliving.mobile.presentation.ui.activity.transport

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ecoliving.mobile.data.Result
import com.ecoliving.mobile.data.pref.UserModel
import com.ecoliving.mobile.data.remote.repository.TransportRepository
import com.ecoliving.mobile.data.remote.response.AddTransportResponse
import kotlinx.coroutines.launch

class TransportViewModel (private val repository: TransportRepository) : ViewModel() {
    private val _addTransport = MutableLiveData<Result<AddTransportResponse>>()
    val addTransport: LiveData<Result<AddTransportResponse>> get() = _addTransport

    fun addTransportActivity(id: Int, type: String, distance: Int) {
        _addTransport.value = Result.Loading
        viewModelScope.launch {
            val addTransport = repository.addTransportActivity(id, type, distance)
            _addTransport.value = addTransport
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}