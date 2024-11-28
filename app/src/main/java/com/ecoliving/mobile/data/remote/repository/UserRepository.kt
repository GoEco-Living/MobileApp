package com.ecoliving.mobile.data.remote.repository

import com.ecoliving.mobile.data.pref.UserModel
import com.ecoliving.mobile.data.pref.UserPreference
import com.ecoliving.mobile.data.remote.response.LoginResponse
import com.ecoliving.mobile.data.remote.response.RegisterResponse
import com.ecoliving.mobile.data.remote.retrofit.ApiService
import com.ecoliving.mobile.data.Result


data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
) {

    suspend fun registerUser(
        name: String,
        email: String,
        password: String
    ): Result<RegisterResponse> {
        return try {
            val registerRequest = RegisterRequest(name, email, password)
            val response = apiService.register(registerRequest)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error("Register failed")
        }
    }

    suspend fun loginUser(email: String, password: String): Result<LoginResponse> {
        val loginRequest = LoginRequest(email, password)

        return try {
            val response = apiService.login(loginRequest)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error("Login failed")
        }
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }


    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }
}