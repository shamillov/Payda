package com.shamilov.payda.presentation.ui.fund

import com.shamilov.common.base.BaseView
import com.shamilov.payda.domain.model.FundEntity
import moxy.viewstate.strategy.alias.OneExecution

/**
 * Created by Shamilov on 29.12.2020
 */
interface FundView : BaseView {
    @OneExecution
    fun setFund(fund: FundEntity)
}