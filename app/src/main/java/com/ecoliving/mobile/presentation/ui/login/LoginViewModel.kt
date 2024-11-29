package com.ecoliving.mobile.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecoliving.mobile.data.pref.UserModel
import com.ecoliving.mobile.data.remote.repository.UserRepository
import com.ecoliving.mobile.data.remote.response.LoginResponse
import kotlinx.coroutines.launch
import com.ecoliving.mobile.data.Result


class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    private val _loginUser = MutableLiveData<Result<LoginResponse>>()
    val loginUser: LiveData<Result<LoginResponse>> get() = _loginUser

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun login(email: String, password: String) {
        _loginUser.value = Result.Loading
        viewModelScope.launch {
            val loginUser = repository.loginUser(email, password)
            _loginUser.value = loginUser
        }
    }
}