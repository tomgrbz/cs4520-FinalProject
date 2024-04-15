package com.example.cs4520_twitter.vms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cs4520_twitter.application.BabbleApplication
import com.example.cs4520_twitter.data_layer.api.BabApi
import com.example.cs4520_twitter.data_layer.database.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddBabViewModel(val babApi: BabApi) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(true);
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    fun addBab(babContent : String, user : UserEntity) {
        // the api currently doesn't have a method for adding a bab, but this was also
        // something we had not planned in the initial project proposal
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return AddBabViewModel((application as BabbleApplication).appContainer.babsService) as T
            }
        }
    }
}