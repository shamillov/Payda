package com.shamilov.payda.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import org.koin.core.KoinComponent

/**
 * Created by Shamilov on 12.08.2020
 */
abstract class BasePresenter<View: BaseView> : MvpPresenter<View>(), KoinComponent {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun launch(disposable: () -> Disposable) {
        compositeDisposable.add(disposable())
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}