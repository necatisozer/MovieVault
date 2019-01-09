package com.necatisozer.movievault.ui.main.movielist

import androidx.lifecycle.MutableLiveData
import com.necatisozer.movievault.data.repository.MovieRepository
import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.ui.base.BaseViewModel
import com.necatisozer.movievault.utils.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val logger: Logger
) : BaseViewModel() {
    internal val movieListLiveData = MutableLiveData<List<Movie>>()

    fun init() {
        subscription(
            movieRepository.getNowPlayingMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                    onNext = { movieListLiveData.value = it },
                    onError = { logger.w(it) }
                )
        )
    }
}
