package com.shamilov.payda.ui.presenters

import com.shamilov.payda.domain.interactor.GetActiveDonationUseCase
import com.shamilov.payda.ui.views.DonationActiveView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationActivePresenter(private val donationUseCase: GetActiveDonationUseCase) {

    private val TAG: String = DonationCompletedPresenter::class.java.simpleName

    private val disposable = CompositeDisposable()
    private lateinit var view: DonationActiveView

    fun getData(hasNetwork: Boolean) {
        if (hasNetwork)
            getDonation()
        else
            view.showNetworkError()
    }

    fun attachView(view: DonationActiveView) {
        this.view = view
    }

    private fun getDonation() {
        view.showProgressBar()

        disposable.addAll(donationUseCase.execute()
            .subscribe({
                view.hideProgressBar()
                view.onSuccess(it)
            }, {
                view.hideProgressBar()
                view.onFailure(it.toString())
            })
        )
    }

    fun onStop() {
        if (!disposable.isDisposed) {
            disposable.clear()
        }
    }
}