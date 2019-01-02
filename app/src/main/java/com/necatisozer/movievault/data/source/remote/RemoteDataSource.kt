package com.necatisozer.movievault.data.source.remote

import com.necatisozer.movievault.data.source.DataSource
import com.necatisozer.movievault.data.source.local.objectbox.entity.Movie
import io.reactivex.Observable

interface RemoteDataSource : DataSource {
    fun getPopularMovies(): Observable<List<Movie>>
    fun getNowPlayingMovies(): Observable<List<Movie>>
}