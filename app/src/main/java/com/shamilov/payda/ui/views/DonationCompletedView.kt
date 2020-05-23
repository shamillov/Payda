package com.shamilov.payda.ui.views

import com.shamilov.payda.data.model.DonationCompletedData
import com.shamilov.payda.ui.interfaces.BaseView

interface DonationCompletedView: BaseView {
    fun onSuccess(data: List<DonationCompletedData>)
    fun onFailure(error: String)
    fun showNetworkError()
}