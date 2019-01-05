package com.necatisozer.movievault.data.repository

import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.data.repository.mapper.mapToMovieEntity
import com.necatisozer.movievault.data.source.tmdb.TmdbApi
import com.necatisozer.movievault.utils.DeviceUtils
import io.reactivex.Observable
import javax.inject.Inject

class ProdMovieRepository @Inject constructor(
    private val tmdbApi: TmdbApi,
    private val deviceUtils: DeviceUtils
) : MovieRepository {
    override fun getPopularMovies(): Observable<List<Movie>> {
        return tmdbApi.getPopularMovies(1)
            .map { movieResults -> movieResults.results.map { it.mapToMovieEntity() } }
            .toObservable()
    }

    override fun getNowPlayingMovies(): Observable<List<Movie>> {
        return tmdbApi.getNowPlayingMovies(1)
            .map { movieResults -> movieResults.results.map { it.mapToMovieEntity() } }
            .toObservable()
    }
}