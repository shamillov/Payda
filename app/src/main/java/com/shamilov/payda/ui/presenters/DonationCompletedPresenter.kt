package com.shamilov.payda.ui.presenters

import com.shamilov.payda.domain.interactor.GetCompletedDonationUseCase
import com.shamilov.payda.ui.views.DonationCompletedView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationCompletedPresenter @Inject constructor(private val donationUseCase: GetCompletedDonationUseCase) {

    private val TAG: String = DonationCompletedPresenter::class.java.simpleName

    private val disposable = CompositeDisposable()
    private lateinit var view: DonationCompletedView

    fun getData(hasNetwork: Boolean) {
        if (hasNetwork)
            getDonation()
        else
            view.showNetworkError()
    }

    fun attachView(view: DonationCompletedView) {
        this.view = view
    }

    private fun getDonation() {
        view.showProgressBar()
    }

    fun onDestroy() {

    }
}