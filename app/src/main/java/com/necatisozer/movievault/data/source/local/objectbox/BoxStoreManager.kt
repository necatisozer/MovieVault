package com.necatisozer.movievault.data.source.local.objectbox

import android.app.Application
import com.necatisozer.movievault.data.source.local.objectbox.entity.MyObjectBox

class BoxStoreManager(val application: Application) {
    val boxStore = MyObjectBox.builder().androidContext(application).build()
}