package com.example.cs4520_twitter.vms

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cs4520_twitter.application.BabbleApplication
import com.example.cs4520_twitter.data_layer.api.BabApi
import com.example.cs4520_twitter.data_layer.database.BabEntity
import com.example.cs4520_twitter.data_layer.database.dummyBab
import com.example.cs4520_twitter.repositories.BabRepo
import com.example.cs4520_twitter.repositories.BabRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchScreenViewModel(private val babApi: BabApi, private val babRepo: BabRepository): ViewModel() {
    private val _results = MutableStateFlow<List<BabEntity>>(emptyList())
    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()
    val results: StateFlow<List<BabEntity>> get() = _results.asStateFlow()

    /**
     * Attempts to search for the input text
     * Updates the results field
     */
    fun search(input: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val resp = babRepo.searchBabsWithString(input)
                if (resp != null) {
                    _results.value = resp
                }
                Log.i("BabFeedViewModel", "Search babs." + resp)
                _loading.value = false
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
                return SearchScreenViewModel((application as BabbleApplication).appContainer.babsService,
                (application as BabbleApplication).appContainer.babRepo) as T
            }
        }
    }
}