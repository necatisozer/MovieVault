package com.necatisozer.movievault.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    protected fun addDisposable(vararg disposables: Disposable) {
        disposables.forEach { this.disposables.add(it) }
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}