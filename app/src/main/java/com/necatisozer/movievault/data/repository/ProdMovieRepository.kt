package com.necatisozer.movievault.data.repository

import com.necatisozer.movievault.data.repository.mapper.mapToCastAndCrewList
import com.necatisozer.movievault.data.repository.mapper.mapToMovieEntity
import com.necatisozer.movievault.data.repository.mapper.mapToMovieList
import com.necatisozer.movievault.data.source.rxpaper.RxMovieBook
import com.necatisozer.movievault.data.source.tmdb.TmdbApi
import com.necatisozer.movievault.domain.entity.Cast
import com.necatisozer.movievault.domain.entity.Crew
import com.necatisozer.movievault.domain.entity.Movie
import com.necatisozer.movievault.domain.repository.MovieRepository
import com.necatisozer.movievault.helper.Logger
import com.pacoworks.rxpaper2.RxPaperBook
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import splitties.systemservices.connectivityManager
import javax.inject.Inject

class ProdMovieRepository @Inject constructor(
    @RxMovieBook private val rxMovieBook: RxPaperBook,
    private val tmdbApi: TmdbApi,
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

        return if (connectivityManager.activeNetworkInfo?.isConnected == true)
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

        return if (connectivityManager.activeNetworkInfo?.isConnected == true)
            Single.concat(storedMovies, updatedMovies).toObservable()
        else storedMovies.toObservable()
    }

    override fun searchMovie(query: String): Observable<List<Movie>> =
        tmdbApi.searchMovie(query, 1).map { it.mapToMovieList() }.toObservable()

    override fun getMovie(id: Int): Observable<Movie> =
        tmdbApi.getMovieById(id.toString()).map { it.mapToMovieEntity() }.toObservable()

    override fun getCredits(id: Int): Observable<Pair<List<Cast>, List<Crew>>> =
        tmdbApi.getMovieCredits(id.toString()).map { it.mapToCastAndCrewList() }.toObservable()

    companion object RxMovieBookKeys {
        private const val popular_movies = "popular_movies"
        private const val now_playing_movies = "now_playing_movies"
    }
}