package com.shamilov.payda.presentation.ui.donation.active

import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.base.BaseView
import com.shamilov.payda.presentation.base.LoadingView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
@StateStrategyType(AddToEndSingleStrategy::class)
interface DonationActiveView : LoadingView {
    fun onSuccess(data: List<DonationEntity>)
    fun onFailure(error: String)
    fun openDonation(donation: DonationEntity)
    fun donate(donationId: Int)
    fun shareDonation()
    fun addToFavorite(isFavorite: Boolean)
}