package com.example.cs4520_twitter.vms

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cs4520_twitter.app_state.LoggedInUser
import com.example.cs4520_twitter.application.BabbleApplication
import com.example.cs4520_twitter.data_layer.api.FollowsApi
import com.example.cs4520_twitter.data_layer.api.ProfilesApi
import com.example.cs4520_twitter.data_layer.api.UsersApi
import com.example.cs4520_twitter.data_layer.api.models.UserIDBodyRequest
import com.example.cs4520_twitter.data_layer.database.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class FollowingScreenViewModel(
    private val followApi : FollowsApi,
    private val profilesApi: ProfilesApi,
    private val userApi : UsersApi // do we need?
): ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(true);
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    private val _following = MutableStateFlow<List<UserEntity>>(emptyList()) // babs of logged in user
    val following: StateFlow<List<UserEntity>> get() = _following.asStateFlow()

    private val _count = MutableStateFlow<Int>(0);
    val count: StateFlow<Int> get() = _count.asStateFlow()

    private val _username = MutableStateFlow<String>("")
    val username: StateFlow<String> get() = _username.asStateFlow()

    private val loggedUUID = UUID.fromString(LoggedInUser.loggedInUserId)
    fun unfollow(usertoUnfollowId: String) {
        val toUnfollowUUID = UUID.fromString(usertoUnfollowId)

        viewModelScope.launch {
            try {
                val resp = followApi.unfollowUser(toUnfollowUUID, UserIDBodyRequest(LoggedInUser.loggedInUserId))
                Log.i("FollowingScreenViewModel", "Successful unfollowing of $toUnfollowUUID")
                fetchData()
            } catch (e: Exception) {
                Log.e("FollowingScreenViewModel", "Failed to fetch resp due to $e")
            }
        }
    }

    fun fetchData() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val resp = profilesApi.getUserProfile(loggedUUID)
                Log.i("FollowingScreenViewModel", "Obtained logged in user following $loggedUUID")
                _isLoading.value = false
                _following.value = resp.profile.followerList
                _count.value = resp.profile.followingCount
                _username.value = resp.profile.user.username
            } catch (e: Exception) {
                Log.e("FollowingScreenViewModel", "Failed to fetch resp due to $e")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return FollowingScreenViewModel((application as BabbleApplication).appContainer.followsApiService,
                    (application as BabbleApplication).appContainer.profilesApiService,
                    (application as BabbleApplication).appContainer.usersApiService) as T
            }
        }
    }
}