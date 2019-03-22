package com.necatisozer.movievault.extension

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun Completable.doInBackground(): Completable =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())