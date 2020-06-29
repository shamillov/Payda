package com.shamilov.payda.ui.views

import com.shamilov.payda.domain.model.FeeEntity
import com.shamilov.payda.ui.interfaces.BaseView

interface DonationActiveView : BaseView {
    fun onSuccess(data: List<FeeEntity>)
    fun onFailure(error: String)
    fun showNetworkError()
}