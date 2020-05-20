package com.shamilov.payda.ui.presenters

import android.os.Handler
import com.shamilov.payda.data.models.Donation
import com.shamilov.payda.ui.views.DonationActiveView

class DonationActivePresenter(private val view: DonationActiveView) {

    fun getData(hasNetwork: Boolean) {
        if (hasNetwork)
            getDonation()
        else
            view.showNetworkError()
    }

    private fun getDonation() {
        view.showProgressBar()

        val handler = Handler()
        handler.postDelayed(Runnable {
            var item1 = Donation("grozny")
            var item12 = Donation("grozny")
            var item13 = Donation("grozny")
            var item14 = Donation("grozny")
            var item15 = Donation("grozny")
            var item16 = Donation("grozny")

            val list: List<Donation> = listOf(item1, item12, item13, item14, item15, item16)

            view.onSuccess(list)
            view.hideProgressBar()
        }, 1000)
    }

    fun onDestroy() {

    }
}