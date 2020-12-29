package com.shamilov.payda.presentation.ui.funds

import com.shamilov.common.base.BaseView
import com.xwray.groupie.Group
import moxy.viewstate.strategy.alias.OneExecution

/**
 * Created by Shamilov on 29.12.2020
 */
interface FundsView : BaseView {
    @OneExecution
    fun setFunds(items: List<Group>)
}