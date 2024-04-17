package com.example.cs4520_twitter.vms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cs4520_twitter.app_state.LoggedInUser
import com.example.cs4520_twitter.application.BabbleApplication
import com.example.cs4520_twitter.data_layer.api.LoginApi
import com.example.cs4520_twitter.data_layer.api.SignupApi
import com.example.cs4520_twitter.data_layer.api.models.CredentialsPostRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginApi: LoginApi,
    private val signupApi: SignupApi
) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(false);
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    private val _success = MutableStateFlow<Boolean>(false);
    val success: StateFlow<Boolean> get() = _success.asStateFlow()

    fun login(username: String, password: String) {
        // validate user credentials
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val resp = loginApi.login(CredentialsPostRequest(username, password))

                LoggedInUser.loggedInUserId = resp.user.userID
                _success.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun signUp(username: String, password: String) {
        // register user, unless one of the username or password is blank
        _isLoading.value = true

        if (username.isNotBlank() and password.isNotBlank()) { // valid fields
            viewModelScope.launch {
                try {
                    val resp = signupApi.signup(CredentialsPostRequest(username, password))
                    LoggedInUser.loggedInUserId = resp.user.userID
                } finally {
                    _isLoading.value = false

                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {

                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return LoginViewModel(
                    (application as BabbleApplication).appContainer.loginApiService,
                    (application as BabbleApplication).appContainer.signupApiService
                ) as T
            }
        }
    }
}