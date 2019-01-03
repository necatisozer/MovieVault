package com.necatisozer.movievault.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val subscriptions = CompositeDisposable()

    protected fun subscription(disposable: Disposable) {
        subscriptions.add(disposable)
    }

    override fun onCleared() {
        subscriptions.dispose()
        super.onCleared()
    }
}