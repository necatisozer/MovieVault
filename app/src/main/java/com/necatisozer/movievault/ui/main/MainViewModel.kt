package com.necatisozer.movievault.ui.main

import androidx.lifecycle.ViewModel
import com.necatisozer.movievault.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    init {
        val disposable = movieRepository.getPopularMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onNext = { print(it) },
                onError = { it.printStackTrace() },
                onComplete = { print("Done") }
            )
    }
}
