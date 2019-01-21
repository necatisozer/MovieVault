package com.necatisozer.movievault.ui.main.moviesearch

import androidx.lifecycle.MutableLiveData
import com.necatisozer.movievault.data.repository.MovieRepository
import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.extension.doInBackground
import com.necatisozer.movievault.helper.Logger
import com.necatisozer.movievault.ui.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MovieSearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val logger: Logger
) : BaseViewModel() {
    val movieListLiveData = MutableLiveData<List<Movie>>()

    fun onQueryTextChange(query: String) {
        addDisposable(
            movieRepository.searchMovie(query).doInBackground().subscribeBy(
                onNext = { movieListLiveData.value = it },
                onError = { logger.w(it) }
            )
        )
    }
}
