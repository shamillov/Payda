package com.shamilov.payda.domain.interactor

import io.reactivex.Observable

/**
 * Created by Shamilov on 15.07.2020
 */
interface ObservableUseCase<T> {
    fun execute(): Observable<List<T>>
}