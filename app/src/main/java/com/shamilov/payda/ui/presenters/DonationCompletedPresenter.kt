package com.shamilov.payda.ui.presenters

import com.shamilov.payda.ui.views.DonationCompletedView

class DonationCompletedPresenter(private val view: DonationCompletedView) {

    private val TAG: String = DonationCompletedPresenter::class.java.simpleName

    fun getData(hasNetwork: Boolean) {
        if (hasNetwork)
            getDonation()
        else
            view.showNetworkError()
    }

    private fun getDonation() {
        view.showProgressBar()
    }

    fun onDestroy() {

    }
}