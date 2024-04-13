package com.example.cs4520_twitter.vms

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cs4520_twitter.app_state.LoggedInUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _isLoading = MutableStateFlow(true);

    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    fun validateCredentials(username: String, password: String) {

        _isLoading.value = true

        viewModelScope.launch {
            try {
                // Mock response from API using repository for users
                val resp = "a mocked json response of user object: {username: username, password: password, userId: userId, imageHref:imageHref"
                LoggedInUser.loggedInUserId = resp // resp.userId
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Failed to verify user credentials")
            }
        }

    }
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return LoginViewModel() as T
            }
        }
    }
}