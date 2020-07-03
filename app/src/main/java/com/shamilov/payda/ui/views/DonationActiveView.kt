package com.shamilov.payda.ui.views

import com.shamilov.payda.domain.model.FeeEntity
import com.shamilov.payda.ui.interfaces.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DonationActiveView : MvpView {
    fun showProgressBar()
    fun hideProgressBar()
    fun onSuccess(data: List<FeeEntity>)
    fun onFailure(error: String)
    fun showNetworkError()
}