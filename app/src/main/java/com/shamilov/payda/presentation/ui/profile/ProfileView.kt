package com.shamilov.payda.presentation.ui.profile

import com.shamilov.common.base.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType


/**
 * Created by Shamilov on 20.07.2020
 */
interface ProfileView : BaseView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setContribution(count: Int)
}