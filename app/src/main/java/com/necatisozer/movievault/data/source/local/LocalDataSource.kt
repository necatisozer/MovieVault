package com.necatisozer.movievault.data.source.local

import com.necatisozer.movievault.data.source.DataSource
import com.necatisozer.movievault.data.source.local.objectbox.entity.Movie
import io.reactivex.Observable

interface LocalDataSource : DataSource {
    fun getPopularMovies(): Observable<List<Movie>>
    fun putPopularMovies(popularMovies: List<Movie>)
    fun getNowPlayingMovies(): Observable<List<Movie>>
}