package com.shamilov.payda.ui.presenters

import com.shamilov.payda.domain.interactor.GetActiveDonationUsecase
import com.shamilov.payda.ui.views.DonationActiveView
import io.reactivex.disposables.CompositeDisposable

class DonationActivePresenter(private val view: DonationActiveView, private val donationUseCase: GetActiveDonationUsecase) {

    private val TAG: String = DonationCompletedPresenter::class.java.simpleName

    private val disposable = CompositeDisposable()

    fun getData(hasNetwork: Boolean) {
        if (hasNetwork)
            getDonation()
        else
            view.showNetworkError()
    }

    private fun getDonation() {
        view.showProgressBar()

        disposable.add(donationUseCase.execute()
            .subscribe({
                view.hideProgressBar()
                view.onSuccess(it)
            }, {
                view.hideProgressBar()
                view.onFailure(it.toString())
            }))
    }

    fun onDestroy() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}