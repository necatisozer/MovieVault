package com.necatisozer.movievault.data.source.local.objectbox

import android.app.Application
import com.necatisozer.movievault.data.source.local.objectbox.entity.MyObjectBox
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BoxStoreManager @Inject constructor(val application: Application) {
    val boxStore = MyObjectBox.builder().androidContext(application).build()
}