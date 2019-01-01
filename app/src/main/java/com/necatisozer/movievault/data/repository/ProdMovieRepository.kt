package com.necatisozer.movievault.data.repository

import com.necatisozer.movievault.data.source.remote.RemoteDataSource
import javax.inject.Inject

class ProdMovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override fun getPopularMovies() = remoteDataSource.getPopularMovies()

    override fun getNowPlayingMovies() = remoteDataSource.getNowPlayingMovies()
}