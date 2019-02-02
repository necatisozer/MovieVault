package com.necatisozer.movievault.ui.main.moviedetail

import androidx.lifecycle.MutableLiveData
import com.necatisozer.movievault.data.repository.MovieRepository
import com.necatisozer.movievault.data.repository.entity.Cast
import com.necatisozer.movievault.data.repository.entity.Crew
import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.extension.doInBackground
import com.necatisozer.movievault.helper.Logger
import com.necatisozer.movievault.ui.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val logger: Logger
) : BaseViewModel() {
    val movieListLiveData = MutableLiveData<Movie>()
    val castListLiveData = MutableLiveData<Pair<List<Cast>, List<Crew>>>()

    fun init(movieId: Int) {
        addDisposable(
            movieRepository.getMovie(movieId).doInBackground().subscribeBy(
                onNext = { movieListLiveData.value = it },
                onError = { logger.w(it) }
            ),
            movieRepository.getCredits(movieId).doInBackground().subscribeBy(
                onNext = { castListLiveData.value = it },
                onError = { logger.w(it) }
            )
        )
    }
}
