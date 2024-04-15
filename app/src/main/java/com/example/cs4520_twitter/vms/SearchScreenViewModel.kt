package com.example.cs4520_twitter.vms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cs4520_twitter.data_layer.database.BabEntity

class SearchScreenViewModel: ViewModel() {
    private val _results = MutableLiveData<List<BabEntity>>()
    val results: LiveData<List<BabEntity>> = _results

    val loading = MutableLiveData<Boolean>(true)
    val noProducts = MutableLiveData<Boolean>(false)

    /**
     * Attempts to search for the input text
     * Updates the results field
     */
    fun search(input: String) {

    }

}