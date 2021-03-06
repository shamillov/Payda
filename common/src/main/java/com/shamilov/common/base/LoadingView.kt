package com.shamilov.common.base

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Created by Shamilov on 12.08.2020
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface LoadingView : BaseView {
    fun showLoading(loading: Boolean)
    fun showSwipeLoading(loading: Boolean)
    fun showNetworkError(showError: Boolean)
}