package com.shamilov.payda.ui.presenters

import android.os.Handler
import com.shamilov.payda.data.models.Donation
import com.shamilov.payda.domain.repository.DonationRepository
import com.shamilov.payda.ui.views.DonationActiveView
import io.reactivex.disposables.CompositeDisposable

class DonationActivePresenter(private val view: DonationActiveView, private val repository: DonationRepository) {
    private val disposable = CompositeDisposable()

    fun getData(hasNetwork: Boolean) {
        if (hasNetwork)
            getDonation()
        else
            view.showNetworkError()
    }

    private fun getDonation() {
        view.showProgressBar()

//        disposable.add(repository.getActiveDonation()
//            .subscribe({
//                view.onSuccess(it.toString())
//                view.hideProgressBar()
//            }, {
//                view.hideProgressBar()
//                view.onFailure(it.toString())}))

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
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}