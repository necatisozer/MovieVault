package com.necatisozer.movievault.ui.main

import androidx.lifecycle.MutableLiveData
import com.necatisozer.movievault.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {
    val queryLiveData = MutableLiveData<String>()

    fun onQueryTextChange(query: String) {
        queryLiveData.value = query
    }
}