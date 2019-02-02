package com.necatisozer.movievault.app.initializer

import android.app.Application
import com.pacoworks.rxpaper2.RxPaperBook
import javax.inject.Inject

class RxPaperInitializer @Inject constructor() : AppInitializer {
    override fun init(application: Application) {
        RxPaperBook.init(application)
    }
}