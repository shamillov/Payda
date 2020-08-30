package com.shamilov.payda.extension

import com.shamilov.payda.presentation.base.LoadingView
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Shamilov on 15.08.2020
 */
fun <T> Single<T>.ioToUi(): Single<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.ioToUi(): Observable<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.loading(view: LoadingView): Single<T> = this
    .doOnSubscribe { view.showLoading(true) }
    .doAfterTerminate { view.showLoading(false) }
