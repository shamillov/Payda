package com.shamilov.payda.domain.interactor

import io.reactivex.Observable

interface ObservableUseCase<T> {
    fun execute(): Observable<List<T>>
}