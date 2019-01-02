package com.necatisozer.movievault.data.repository

import com.necatisozer.movievault.data.source.local.LocalDataSource
import com.necatisozer.movievault.data.source.remote.RemoteDataSource
import io.reactivex.Observable
import javax.inject.Inject

class ProdMovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MovieRepository {
    override fun getPopularMovies() =
        Observable.concat(remoteDataSource.getPopularMovies(), localDataSource.getPopularMovies())

    override fun getNowPlayingMovies() = Observable.concat(
        remoteDataSource.getNowPlayingMovies(),
        localDataSource.getNowPlayingMovies()
    )
}