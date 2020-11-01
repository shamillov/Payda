package com.shamilov.payda.extensions

import com.shamilov.common.base.LoadingView
import io.reactivex.Completable
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

fun Completable.ioToUi(): Completable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.loading(view: LoadingView): Single<T> = this
    .doOnSubscribe { view.showLoading(true) }
    .doAfterTerminate { view.showLoading(false) }
