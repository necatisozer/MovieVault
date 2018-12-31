package com.necatisozer.movievault.data.source.remote

import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.data.source.remote.tmdb.TmdbApi
import com.necatisozer.movievault.data.source.remote.tmdb.mapToMovieEntity
import io.reactivex.Observable
import javax.inject.Inject

class ProdRemoteDataSource @Inject constructor(
    private val tmdbApi: TmdbApi
) : RemoteDataSource {
    override fun getPopularMovies(): Observable<List<Movie>> {
        return tmdbApi.getPopularMovies(1)
            .map { movieResults -> movieResults.results.map { result -> result.mapToMovieEntity() } }
            .toObservable()
    }

    override fun getNowPlayingMovies(): Observable<List<Movie>> {
        return tmdbApi.getNowPlayingMovies(1)
            .map { movieResults -> movieResults.results.map { result -> result.mapToMovieEntity() } }
            .toObservable()
    }
}