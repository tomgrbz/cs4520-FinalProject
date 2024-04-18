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
import com.example.cs4520_twitter.data_layer.api.models.UserIDBodyRequest
import com.example.cs4520_twitter.data_layer.database.BabEntity
import com.example.cs4520_twitter.data_layer.database.UserProfileEntity
import com.example.cs4520_twitter.data_layer.database.dummyProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.util.UUID

class BabCardViewModel(private val babApi: BabApi,
                       private val profileApi: ProfilesApi
) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(true);
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    private val _loggedInProfile =
        MutableStateFlow<UserProfileEntity>(dummyProfile) // logged in profile
    val loggedInProfile: StateFlow<UserProfileEntity> get() = _loggedInProfile.asStateFlow()

    private val _babLikeCount = MutableStateFlow<Int>(0) // logged in profile
    val babLikeCount: StateFlow<Int> get() = _babLikeCount.asStateFlow()

    private val _listUsersThatLiked = MutableStateFlow<MutableList<String>>(mutableListOf()) // logged in profile
    val listUsersThatLiked: StateFlow<MutableList<String>> get() = _listUsersThatLiked.asStateFlow()

    fun setBabLikeCount(count : Int) {
        _babLikeCount.value = count
    }

    fun setUsersThatLiked(list : MutableList<String>) {
        _listUsersThatLiked.value = list
    }

    fun fetchLoggedInUserProfile() { // as a workaround, I think I can just grab the user's profile and their user entity from there
        val loggedUUID = UUID.fromString(LoggedInUser.loggedInUserId)
        viewModelScope.launch {
            try {
                val resp = profileApi.getUserProfile(loggedUUID)
                Log.i("BabCardViewModel", "Obtained logged in user profile " + resp)

                _loggedInProfile.value = resp.profile
            } catch (e: Exception) {
                Log.e("BabCardViewModel", "Failed to fetch resp due to $e")
            }
        }
    }

    fun likeBab(babId : Int) { // like a bab
        val loggedUUID = UUID.fromString(LoggedInUser.loggedInUserId)
        viewModelScope.launch {
            try {
                val resp = babApi.likeBab(babId, UserIDBodyRequest(LoggedInUser.loggedInUserId))
                Log.i("BabCardViewModel", "Liked  a bab response with given babID $babId" + resp)
                _babLikeCount.value += 1
                _listUsersThatLiked.value.add(LoggedInUser.loggedInUserId)
            } catch (e: Exception) {
                Log.e("BabCardViewModel", "Failed to like a bab $e")
            }
        }
    }

    fun unLikeBab(babId : Int) { // unlike a bab
        val loggedUUID = UUID.fromString(LoggedInUser.loggedInUserId)
        viewModelScope.launch {
            try {
                val resp = babApi.unlikeBab(babId, UserIDBodyRequest(LoggedInUser.loggedInUserId))
                Log.i("BabCardViewModel", "Unliked a bab response with given babID $babId" + resp)
                _babLikeCount.value -= 1
                _listUsersThatLiked.value.filter { s ->  s != LoggedInUser.loggedInUserId}
            } catch (e: Exception) {
                Log.e("BabCardViewModel", "Failed to unlike a bab $e")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return BabCardViewModel(
                    (application as BabbleApplication).appContainer.babsService,
                    application.appContainer.profilesApiService
                ) as T
            }
        }
    }
}