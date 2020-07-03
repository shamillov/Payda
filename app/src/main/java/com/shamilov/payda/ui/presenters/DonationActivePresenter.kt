package com.shamilov.payda.ui.presenters

import com.shamilov.payda.domain.interactor.GetActiveDonationUseCase
import com.shamilov.payda.ui.views.DonationActiveView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.operators.observable.ObservableFromIterable
import moxy.InjectViewState
import moxy.MvpPresenter

/**
 * Created by Shamilov on 20.05.2020
 */

@InjectViewState
class DonationActivePresenter(private val donationUseCase: GetActiveDonationUseCase) : MvpPresenter<DonationActiveView>() {

    private val TAG: String = DonationCompletedPresenter::class.java.simpleName

    private val disposable = CompositeDisposable()
    private lateinit var view: DonationActiveView

    fun getData(hasNetwork: Boolean) {
        if (hasNetwork)
            getDonation()
        else
            viewState.showNetworkError()
    }

//    fun attachView(view: DonationActiveView) {
//        this.view = view
//    }

    private fun getDonation() {
        viewState.showProgressBar()

        disposable.add(donationUseCase.execute()
            .subscribe({
                viewState.hideProgressBar()
                viewState.onSuccess(it)
            }, {
                viewState.hideProgressBar()
                viewState.onFailure(it.toString())
            })
        )
    }

    fun refreshData() {
        disposable.add(donationUseCase.execute()
            .subscribe({
                viewState.onSuccess(it)
            }, {
                viewState.onFailure(it.toString())
            })
        )
    }

//    fun onStop() {
//        if (!disposable.isDisposed) {
//            disposable.clear()
//        }
//    }
}