package com.necatisozer.movievault.data.source.tmdb

import com.necatisozer.movievault.data.source.tmdb.entity.Configurations
import com.necatisozer.movievault.data.source.tmdb.entity.Credits
import com.necatisozer.movievault.data.source.tmdb.entity.Jobs
import com.necatisozer.movievault.data.source.tmdb.entity.Movie
import com.necatisozer.movievault.data.source.tmdb.entity.MovieResults
import com.serjltt.moshi.adapters.Wrapped
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("configuration")
    fun getApiConfiguration(): Single<Configurations>

    @GET("configuration/jobs")
    fun getJobs(): Single<List<Jobs>>

    @GET("genre/movie/list")
    @Wrapped(path = ["genres"])
    fun getGenres(): Single<List<Movie.Genre>>

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Single<MovieResults>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("page") page: Int): Single<MovieResults>

    @GET("movie/{movie_id}")
    fun getMovieById(@Path("movie_id") movieId: String): Single<Movie>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") movieId: String): Single<Credits>

    @GET("search/movie")
    fun searchMovie(@Query("query") query: String, @Query("page") page: Int): Single<MovieResults>
}