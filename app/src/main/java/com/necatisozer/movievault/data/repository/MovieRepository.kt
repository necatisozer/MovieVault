package com.necatisozer.movievault.data.repository

import com.necatisozer.movievault.data.repository.entity.Movie
import io.reactivex.Observable

interface MovieRepository : Repository {
    fun getPopularMovies(): Observable<List<Movie>>
    fun getNowPlayingMovies(): Observable<List<Movie>>
    fun searchMovie(query: String): Observable<List<Movie>>
}