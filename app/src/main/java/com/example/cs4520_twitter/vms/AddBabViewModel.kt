package com.example.cs4520_twitter.vms

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cs4520_twitter.app_state.LoggedInUser
import com.example.cs4520_twitter.application.BabbleApplication
import com.example.cs4520_twitter.data_layer.api.BabApi
import com.example.cs4520_twitter.data_layer.api.ProfilesApi
import com.example.cs4520_twitter.data_layer.api.UsersApi
import com.example.cs4520_twitter.data_layer.api.models.AddBabRequest
import com.example.cs4520_twitter.data_layer.database.UserEntity
import com.example.cs4520_twitter.data_layer.database.UserProfileEntity
import com.example.cs4520_twitter.data_layer.database.dummyProfile
import com.example.cs4520_twitter.data_layer.database.dummyUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class AddBabViewModel(private val babApi: BabApi,
                      private val profileApi: ProfilesApi) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(true);
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    private val _loggedInProfile = MutableStateFlow<UserProfileEntity>(dummyProfile) // logged in profile
    val loggedInProfile: StateFlow<UserProfileEntity> get() = _loggedInProfile.asStateFlow()

    fun fetchLoggedInUserProfile() { // as a workaround, I think I can just grab the user's profile and their user entity from there
        val loggedUUID = UUID.fromString(LoggedInUser.loggedInUserId)
        viewModelScope.launch {
            try {
                val resp = profileApi.getUserProfile(loggedUUID)
                Log.i("AddBabViewModel", "Obtained logged in user profile " + resp)

                _loggedInProfile.value = resp.profile
            } catch (e: Exception) {
                Log.e("AddBabViewModel", "Failed to fetch resp due to $e")
            }
        }
    }

    fun addBabForLoggedInUser(babContent : String) {
        val loggedUUID = UUID.fromString(LoggedInUser.loggedInUserId)

        viewModelScope.launch {
            try {
                val resp = babApi.addBab(loggedUUID, AddBabRequest( babContent))
                Log.i("AddBabViewModel", "Added a bab response " + resp)

            } catch (e: Exception) {
                Log.e("AddBabViewModel", "Failed to add a bab resp $e")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return AddBabViewModel((application as BabbleApplication).appContainer.babsService,
                    application.appContainer.profilesApiService) as T
            }
        }
    }
}