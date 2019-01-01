package com.necatisozer.movievault.data.source.remote

import com.necatisozer.movievault.data.source.remote.tmdb.TmdbApi
import com.necatisozer.movievault.data.source.remote.tmdb.mapToMovieEntity
import javax.inject.Inject

class ProdRemoteDataSource @Inject constructor(
    private val tmdbApi: TmdbApi
) : RemoteDataSource {
    override fun getPopularMovies() = tmdbApi.getPopularMovies(1)
        .map { movieResults -> movieResults.results.map { result -> result.mapToMovieEntity() } }
        .toObservable()

    override fun getNowPlayingMovies() = tmdbApi.getNowPlayingMovies(1)
        .map { movieResults -> movieResults.results.map { result -> result.mapToMovieEntity() } }
        .toObservable()
}