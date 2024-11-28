package com.ecoliving.mobile.presentation.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecoliving.mobile.data.remote.repository.UserRepository
import com.ecoliving.mobile.data.remote.response.RegisterResponse
import kotlinx.coroutines.launch
import com.ecoliving.mobile.data.Result

class RegisterViewModel (private val repository: UserRepository) : ViewModel() {
    private val _signUpUser = MutableLiveData <Result<RegisterResponse>>()
    val signUpUser: LiveData<Result<RegisterResponse>> get() = _signUpUser

    fun signUp(name: String ,email: String, password: String) {
        viewModelScope.launch {
            _signUpUser.value = Result.Loading
            viewModelScope.launch {
                val signUpUser = repository.registerUser(name ,email, password)
                _signUpUser.value = signUpUser
            }
        }
    }}