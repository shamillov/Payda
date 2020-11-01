package com.shamilov.payda.presentation.ui.donation.completed

import com.shamilov.common.base.BasePresenter
import moxy.InjectViewState

/**
 * Created by Shamilov on 20.05.2020
 */
@InjectViewState
class DonationCompletedPresenter : BasePresenter<DonationCompletedView>() {

    private val TAG: String = DonationCompletedPresenter::class.java.simpleName

    fun getData(hasNetwork: Boolean) {
        if (hasNetwork)
            getDonation()

//            viewState.showNetworkError()
    }

    private fun getDonation() {
//        viewState.showLoading(true)
    }

}