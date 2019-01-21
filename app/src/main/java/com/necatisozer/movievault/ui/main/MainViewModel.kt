package com.necatisozer.movievault.ui.main

import androidx.lifecycle.MutableLiveData
import com.necatisozer.movievault.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {
    val queryLiveData = MutableLiveData<String>()
    val showMovieDetailLiveData = MutableLiveData<Boolean>()

    fun onQueryTextChange(query: String) {
        queryLiveData.value = query
    }

    fun onShowMovieDetail() {
        showMovieDetailLiveData.value = true
    }
}