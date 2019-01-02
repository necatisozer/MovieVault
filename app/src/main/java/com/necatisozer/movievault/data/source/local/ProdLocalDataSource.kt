package com.necatisozer.movievault.data.source.local

import com.necatisozer.movievault.data.source.local.objectbox.BoxStoreManager
import com.necatisozer.movievault.data.source.local.objectbox.entity.Movie
import io.reactivex.Observable
import javax.inject.Inject

class ProdLocalDataSource @Inject constructor(
    boxStoreManager: BoxStoreManager
) : LocalDataSource {
    val movieBox = boxStoreManager.boxStore.boxFor(Movie::class.java)

    override fun getPopularMovies() = Observable.just(movieBox.all)

    override fun getNowPlayingMovies() = Observable.just(movieBox.all)
}