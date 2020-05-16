package com.shamilov.payda.ui.views

import com.shamilov.payda.data.models.Donation
import com.shamilov.payda.ui.interfaces.BaseView

interface ActiveView: BaseView {
    fun onSuccess(data: List<Donation>)
    fun onFailure(error: String)
}