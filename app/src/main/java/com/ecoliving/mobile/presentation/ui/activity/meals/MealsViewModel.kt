package com.ecoliving.mobile.presentation.ui.activity.meals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ecoliving.mobile.data.Result
import com.ecoliving.mobile.data.pref.UserModel
import com.ecoliving.mobile.data.remote.repository.MealsRepository
import com.ecoliving.mobile.data.remote.response.MealsResponse
import kotlinx.coroutines.launch

class MealsViewModel (private val repository: MealsRepository) : ViewModel() {
    private val _addMeals = MutableLiveData<Result<MealsResponse>>()
    val addMeals: LiveData<Result<MealsResponse>> get() = _addMeals

    fun addMealsActivity(userId: Int, type: String) {
        _addMeals.value = Result.Loading
        viewModelScope.launch {
            val addMeals = repository.addMealsActivity(userId, type)
            _addMeals.value = addMeals
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}