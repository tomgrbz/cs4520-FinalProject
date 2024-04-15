package com.example.cs4520_twitter.vms

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cs4520_twitter.application.BabbleApplication
import com.example.cs4520_twitter.data_layer.api.BabApi
import com.example.cs4520_twitter.data_layer.database.BabEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BabFeedViewModel(private val babApi : BabApi) : ViewModel() {


    private val _babList = MutableStateFlow<List<BabEntity>>(emptyList())
    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()
    val babList: StateFlow<List<BabEntity>> get() = _babList.asStateFlow()

    fun fetchBabs() {
        // method for filling the bab feed

        _isLoading.value = true

        viewModelScope.launch {
            try {
                val resp = babApi.getRandomBabs()
                Log.i("BabFeedViewModel", "Fetched random babs.")
                _babList.value = resp.babs

            } catch (e: Exception) {
                Log.e("BabFeedViewModel", "Failed to fetch resp due to $e")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return BabFeedViewModel((application as BabbleApplication).appContainer.babsService) as T
            }
        }
    }
}