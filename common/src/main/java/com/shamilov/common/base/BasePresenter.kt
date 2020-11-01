package com.shamilov.common.base

import com.shamilov.common.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import org.koin.core.KoinComponent
import retrofit2.HttpException
import java.net.UnknownHostException

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

    protected fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
        when (throwable) {
            is UnknownHostException -> viewState.showMessage(R.string.connection_error_message)
            is HttpException -> when(throwable.code()) {
                404 -> viewState.showMessage(R.string.not_found_error_message)
                500 -> viewState.showMessage(R.string.server_error_message)
            }
            else -> viewState.showMessage(R.string.error_happened)
        }
    }
}