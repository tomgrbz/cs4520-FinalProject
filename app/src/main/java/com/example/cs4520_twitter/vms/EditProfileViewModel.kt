package com.example.cs4520_twitter.vms

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cs4520_twitter.app_state.LoggedInUser
import com.example.cs4520_twitter.application.BabbleApplication
import com.example.cs4520_twitter.data_layer.api.BabApi
import com.example.cs4520_twitter.data_layer.api.ProfilesApi
import com.example.cs4520_twitter.data_layer.api.UsersApi
import com.example.cs4520_twitter.data_layer.api.models.EditDescriptionRequest
import com.example.cs4520_twitter.data_layer.api.models.EditUsernameRequest
import com.example.cs4520_twitter.data_layer.database.BabEntity
import com.example.cs4520_twitter.data_layer.database.UserProfileEntity
import com.example.cs4520_twitter.data_layer.database.dummyProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class EditProfileViewModel(val profileApi : ProfilesApi,
                           val userApi : UsersApi): ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(true);
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    private val _babList = MutableStateFlow<List<BabEntity>>(emptyList()) // babs of logged in user
    val babList: StateFlow<List<BabEntity>> get() = _babList.asStateFlow()

    private val _loggedInProfile = MutableStateFlow<UserProfileEntity>(dummyProfile) // logged in profile
    val loggedUUID = UUID.fromString(LoggedInUser.loggedInUserId)

    val loggedInProfile: StateFlow<UserProfileEntity> get() = _loggedInProfile.asStateFlow()

    fun updateDesc(newDesc: String) {
        viewModelScope.launch {
            try {
                val resp = profileApi.changeUserProfileDescription(loggedUUID,
                    EditDescriptionRequest(newDesc)
                )
                Log.i("EditProfileViewModel", "Edited user desc " + resp)
            } catch (e: Exception) {
                Log.e("EditProfileViewModel", "Failed to edit resp due to $e")
            }
        }
    }

    fun updateName(newName: String) {
        viewModelScope.launch {
            try {
                val resp = profileApi.changeUserProfileName(loggedUUID,
                    EditUsernameRequest(newName)
                )
                Log.i("EditProfileViewModel", "Edited user name " + resp)
            } catch (e: Exception) {
                Log.e("EditProfileViewModel", "Failed to edit resp due to $e")
            }
        }

    }

    fun toastMessage() {

    }

    fun fetchLoggedInUserProfile() {
        viewModelScope.launch {
            try {
                val resp = profileApi.getUserProfile(loggedUUID)
                Log.i("ProfileViewModel", "Obtained logged in user profile " + resp)

                _loggedInProfile.value = resp.profile
                _isLoading.value = false
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Failed to fetch resp due to $e")
            }
        }
    }

    fun fetchLoggedInUserBabs() {
        val loggedUUID = UUID.fromString(LoggedInUser.loggedInUserId)
        viewModelScope.launch {
            try {
                val resp = userApi.getUserBabs(loggedUUID)
                Log.i("ProfileViewModel", "Obtained logged in user babs " + LoggedInUser.loggedInUserId)
                _babList.value = resp.babs
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Failed to fetch resp due to $e")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return EditProfileViewModel((application as BabbleApplication).appContainer.profilesApiService,
                    (application as BabbleApplication).appContainer.usersApiService) as T
            }
        }
    }
}