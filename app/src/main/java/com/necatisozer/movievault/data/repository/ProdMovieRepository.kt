package com.necatisozer.movievault.data.repository

import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.data.source.remote.RemoteDataSource
import io.reactivex.Observable
import javax.inject.Inject

class ProdMovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override fun getPopularMovies(): Observable<List<Movie>> {
        return remoteDataSource.getPopularMovies()
    }

    override fun getNowPlayingMovies(): Observable<List<Movie>> {
        return remoteDataSource.getNowPlayingMovies()
    }
}