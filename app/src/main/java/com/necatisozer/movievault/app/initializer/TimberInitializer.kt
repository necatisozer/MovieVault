package com.necatisozer.movievault.app.initializer

import android.app.Application
import com.necatisozer.movievault.BuildConfig
import com.necatisozer.movievault.helper.TimberLogger
import javax.inject.Inject

class TimberInitializer @Inject constructor(
    private val timberLogger: TimberLogger
) : AppInitializer {
    override fun init(application: Application) {
        timberLogger.setup(BuildConfig.DEBUG)
    }
}