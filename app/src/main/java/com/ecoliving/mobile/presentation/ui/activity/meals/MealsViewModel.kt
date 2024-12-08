package com.ecoliving.mobile.presentation.ui.activity.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecoliving.mobile.data.remote.repository.MealsRepository
import com.ecoliving.mobile.data.remote.response.MealsResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MealsViewModel(
    private val repository: MealsRepository
) : ViewModel() {

    private val _mealsResponse = MutableStateFlow<MealsResponse?>(null)
    val mealsResponse: StateFlow<MealsResponse?> get() = _mealsResponse

    fun sendMealsData(type: String, predictedEmission: Boolean) {
        viewModelScope.launch {
            try {
                val response = repository.mealsUser(type, predictedEmission)
                _mealsResponse.value = response
            } catch (e: Exception) {
            }
        }
    }
}