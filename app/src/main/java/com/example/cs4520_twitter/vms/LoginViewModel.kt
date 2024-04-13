package com.example.cs4520_twitter.vms

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cs4520_twitter.app_state.LoggedInUser
import com.example.cs4520_twitter.application.BabbleApplication
import com.example.cs4520_twitter.data_layer.api.LoginApi
import com.example.cs4520_twitter.data_layer.api.models.CredentialsPostRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(val loginApi: LoginApi) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(true);

    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()


    fun login(username: String, password: String) {

        _isLoading.value = true

        viewModelScope.launch {
            try {
                val resp = loginApi.login(CredentialsPostRequest(username, password))
                Log.i("LoginViewModel", "Logged in $resp")
                LoggedInUser.loggedInUserId = resp.user.userID
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Failed to fetch resp due to $e")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return LoginViewModel((application as BabbleApplication).appContainer.loginApiService) as T
            }
        }
    }
}