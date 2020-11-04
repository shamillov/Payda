package com.shamilov.common.base

import androidx.annotation.StringRes
import com.shamilov.common.utils.Event
import com.shamilov.common.utils.NavigationCommand
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Created by Shamilov on 12.08.2020
 */
interface BaseView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(message: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(@StringRes id: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigate(event: Event<NavigationCommand>)
}