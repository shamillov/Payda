package com.shamilov.payda.ui.views

import com.shamilov.payda.domain.model.DonationActiveEntity
import com.shamilov.payda.ui.interfaces.BaseView

interface DonationActiveView : BaseView {
    fun onSuccess(data: List<DonationActiveEntity>)
    fun onFailure(error: String)
    fun showNetworkError()
}