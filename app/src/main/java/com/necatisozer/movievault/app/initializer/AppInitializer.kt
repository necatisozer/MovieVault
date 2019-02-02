package com.necatisozer.movievault.app.initializer

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}