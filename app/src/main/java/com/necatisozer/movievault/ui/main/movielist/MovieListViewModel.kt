package com.necatisozer.movievault.ui.main.movielist

import androidx.lifecycle.MutableLiveData
import com.necatisozer.movievault.domain.entity.Movie
import com.necatisozer.movievault.domain.repository.MovieRepository
import com.necatisozer.movievault.extension.doInBackground
import com.necatisozer.movievault.helper.Logger
import com.necatisozer.movievault.ui.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val logger: Logger
) : BaseViewModel() {
    val movieListLiveData = MutableLiveData<List<Movie>>()

    fun init() {
        addDisposable(
            movieRepository.getNowPlayingMovies().doInBackground().subscribeBy(
                onNext = { movieListLiveData.value = it },
                onError = { logger.w(it) }
            )
        )
    }
}
