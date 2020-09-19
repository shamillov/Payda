package com.shamilov.payda.presentation.ui.donation.active

import com.shamilov.payda.domain.interactor.GetDonationUseCase
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.base.BasePresenter
import com.shamilov.payda.presentation.ui.donation.active.viewholder.DonationViewHolder
import com.shamilov.payda.presentation.ui.donation.active.viewholder.HeaderViewHolder
import com.shamilov.payda.presentation.ui.donation.completed.DonationCompletedPresenter
import com.xwray.groupie.Group
import moxy.InjectViewState
import org.koin.core.inject

/**
 * Created by Shamilov on 20.05.2020
 */
@InjectViewState
class DonationActivePresenter : BasePresenter<DonationActiveView>(), DonationViewHolder.DonationListener {

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
            donationUseCase.getDonations()
                .doOnSubscribe { viewState.showSwipeLoading(true) }
                .doOnComplete {
                    viewState.showSwipeLoading(false)
                    viewState.showNetworkError(false)
                    viewState.showEmptyMessage(false)
                }
                .doOnError { viewState.showSwipeLoading(false) }
                .subscribe(
                    { donation ->
                        val items = mutableListOf<Group>()
                        items.add(HeaderViewHolder())
                        items.addAll(donation.map { DonationViewHolder(it, this) })

                        if (donation.isNotEmpty()) {
                            viewState.onUpdate(items)
                        } else {
                            viewState.showEmptyMessage(true)
                        }
                    },
                    { throwable -> handleError(throwable) })
        }
    }

    private fun loadDonations() {
        launch {
            donationUseCase.getDonations()
                .doOnSubscribe { viewState.showLoading(true) }
                .doOnComplete { viewState.showLoading(false) }
                .doOnError { viewState.showLoading(false) }
                .subscribe(
                    { donation ->
                        val items = mutableListOf<Group>()
                        items.add(HeaderViewHolder())
                        items.addAll(donation.map { DonationViewHolder(it, this) })

                        if (donation.isNotEmpty()) {
                            viewState.onSuccess(items)
                        } else {
                            viewState.showEmptyMessage(true)
                        }
                    },
                    { throwable -> handleError(throwable) })
        }
    }

    override fun onItemClick(donation: DonationEntity) {
        viewState.openDonation(donation)
    }

    override fun onDonateClick(donationId: Int) {
        viewState.donate(donationId)
    }

    override fun onShareClick() {
        viewState.shareDonation()
    }

    override fun onFavoriteClick() {

    }
}