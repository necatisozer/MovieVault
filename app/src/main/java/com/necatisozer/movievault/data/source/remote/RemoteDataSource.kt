package com.necatisozer.movievault.data.source.remote

import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.data.source.DataSource
import io.reactivex.Observable

interface RemoteDataSource : DataSource {
    fun getPopularMovies(): Observable<List<Movie>>
    fun getNowPlayingMovies(): Observable<List<Movie>>
}