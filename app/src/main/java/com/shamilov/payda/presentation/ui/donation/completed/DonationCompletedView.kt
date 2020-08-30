package com.shamilov.payda.presentation.ui.donation.completed

import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.base.BaseView
import com.shamilov.payda.presentation.base.LoadingView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Created by Shamilov on 20.05.2020
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface DonationCompletedView : LoadingView {
    fun onSuccess(data: List<DonationEntity>)
    fun onFailure(error: String)
    fun showNetworkError()
}