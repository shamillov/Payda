package com.shamilov.payda.presentation.ui.donation.active

import com.shamilov.payda.domain.interactor.GetDonationUseCase
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.base.BasePresenter
import com.shamilov.payda.presentation.ui.donation.completed.DonationCompletedPresenter
import moxy.InjectViewState
import org.koin.core.inject

/**
 * Created by Shamilov on 20.05.2020
 */
@InjectViewState
class DonationActivePresenter : BasePresenter<DonationActiveView>() {

    private val TAG: String = DonationCompletedPresenter::class.java.simpleName

    private val donationUseCase: GetDonationUseCase by inject()

    fun loadData(hasNetwork: Boolean) {
        if (hasNetwork) {
            loadDonations()
        } else {
            viewState.showNetworkError(true)
        }
    }

    fun refreshData() {
        launch {
            donationUseCase.execute()
                .doOnSubscribe { viewState.showSwipeLoading(true) }
                .doOnComplete {
                    viewState.showSwipeLoading(false)
                    viewState.showNetworkError(false)
                }
                .doOnError { viewState.showSwipeLoading(false) }
                .subscribe(
                    { viewState.onSuccess(it) },
                    { throwable -> handleError(throwable) })
        }
    }

    fun donationClicked(donation: DonationEntity) {
        viewState.openDonation(donation)
    }

    fun helpClicked(donationId: Int) {
        viewState.donate(donationId)
    }

    fun shareClicked() {
        viewState.shareDonation()
    }

    fun favoriteClicked(isFavorite: Boolean) {
        viewState.addToFavorite(isFavorite)
    }

    private fun loadDonations() {
        launch {
            donationUseCase.execute()
                .doOnSubscribe { viewState.showLoading(true) }
                .doOnComplete { viewState.showLoading(false) }
                .doOnError { viewState.showLoading(false) }
                .subscribe(
                    { viewState.onSuccess(it) },
                    { throwable -> handleError(throwable) })
        }
    }
}