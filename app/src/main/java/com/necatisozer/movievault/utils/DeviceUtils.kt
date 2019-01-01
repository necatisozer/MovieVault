package com.necatisozer.movievault.utils

import android.content.Context
import android.net.ConnectivityManager

object DeviceUtils {
    fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo.isConnected
    }
}