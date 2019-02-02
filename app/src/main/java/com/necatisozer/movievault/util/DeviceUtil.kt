package com.necatisozer.movievault.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class DeviceUtil @Inject constructor(private val application: Application) {
    fun isConnected(): Boolean {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}