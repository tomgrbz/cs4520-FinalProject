package com.example.cs4520_twitter.vms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs4520_twitter.data_layer.database.BabEntity
import com.example.cs4520_twitter.data_layer.database.dummyBab
import kotlinx.coroutines.launch

val dummyBabList = listOf<BabEntity>(dummyBab)
class SearchScreenViewModel: ViewModel() {
    private val _results = MutableLiveData<List<BabEntity>>()
    val results: LiveData<List<BabEntity>> = _results

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    /**
     * Attempts to search for the input text
     * Updates the results field
     */
    fun search(input: String) {
        viewModelScope.launch {
            _loading.value = true
            // attempt load TODO()
            _loading.value = false
            _results.value = dummyBabList
        }
    }

}