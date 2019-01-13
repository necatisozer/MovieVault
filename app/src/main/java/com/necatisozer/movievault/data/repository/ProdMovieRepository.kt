package com.necatisozer.movievault.data.repository

import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.data.repository.mapper.mapToMovieList
import com.necatisozer.movievault.data.source.rxpaper.RxMovieBook
import com.necatisozer.movievault.data.source.tmdb.TmdbApi
import com.necatisozer.movievault.helper.Logger
import com.necatisozer.movievault.util.DeviceUtil
import com.pacoworks.rxpaper2.RxPaperBook
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ProdMovieRepository @Inject constructor(
    @RxMovieBook private val rxMovieBook: RxPaperBook,
    private val tmdbApi: TmdbApi,
    private val deviceUtil: DeviceUtil,
    private val logger: Logger
) : MovieRepository {
    override fun getNowPlayingMovies(): Observable<List<Movie>> {
        val storedMovies = rxMovieBook.read<List<Movie>>(popular_movies, emptyList())
        val updatedMovies = tmdbApi.getNowPlayingMovies(1).map { it.mapToMovieList() }.doOnSuccess {
            rxMovieBook.write(popular_movies, it)
                .subscribeBy(
                    onComplete = { logger.i("Now playing movies were stored") },
                    onError = { logger.w(it, "Now playing movies couldn't be stored") }
                )
        }

        return if (deviceUtil.isConnected())
            Single.concat(storedMovies, updatedMovies).toObservable()
        else storedMovies.toObservable()
    }

    override fun getPopularMovies(): Observable<List<Movie>> {
        val storedMovies = rxMovieBook.read<List<Movie>>(now_playing_movies, emptyList())
        val updatedMovies = tmdbApi.getPopularMovies(1).map { it.mapToMovieList() }.doOnSuccess {
            rxMovieBook.write(now_playing_movies, it).subscribeBy(
                onComplete = { logger.i("Popular movies were stored") },
                onError = { logger.w(it, "Popular movies couldn't be stored") }
            )
        }

        return if (deviceUtil.isConnected())
            Single.concat(storedMovies, updatedMovies).toObservable()
        else storedMovies.toObservable()
    }

    companion object RxMovieBookKeys {
        private const val popular_movies = "popular_movies"
        private const val now_playing_movies = "now_playing_movies"
    }
}