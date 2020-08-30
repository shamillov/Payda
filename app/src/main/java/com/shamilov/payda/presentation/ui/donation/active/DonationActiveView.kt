package com.shamilov.payda.presentation.ui.donation.active

import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.base.LoadingView
import com.xwray.groupie.Group
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Created by Shamilov on 20.07.2020
 */
@StateStrategyType(OneExecutionStateStrategy::class)
interface DonationActiveView : LoadingView {
    fun onSuccess(data: List<Group>)
    fun onFailure(error: String)
    fun onUpdate(data: List<Group>)
    fun openDonation(donation: DonationEntity)
    fun donate(donationId: Int)
    fun shareDonation()
    fun addToFavorite(isFavorite: Boolean)
    fun showEmptyMessage(show: Boolean)
}