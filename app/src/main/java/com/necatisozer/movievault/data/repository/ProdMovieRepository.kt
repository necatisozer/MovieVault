package com.necatisozer.movievault.data.repository

import com.necatisozer.movievault.data.source.local.LocalDataSource
import com.necatisozer.movievault.data.source.local.objectbox.entity.Movie
import com.necatisozer.movievault.data.source.remote.RemoteDataSource
import com.necatisozer.movievault.utils.DeviceUtils
import io.reactivex.Observable
import javax.inject.Inject

class ProdMovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val deviceUtils: DeviceUtils
) : MovieRepository {
    override fun getPopularMovies(): Observable<List<Movie>> {
        val localData = localDataSource.getPopularMovies()
        val remoteData = remoteDataSource.getPopularMovies().doOnNext {
            localDataSource.putPopularMovies(it)
        }
        return if (deviceUtils.isConnected())
            Observable.concat(localData, remoteData) else localData
    }

    override fun getNowPlayingMovies() = Observable.concat(
        remoteDataSource.getNowPlayingMovies(),
        localDataSource.getNowPlayingMovies()
    )
}