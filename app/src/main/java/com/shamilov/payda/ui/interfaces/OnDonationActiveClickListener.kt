package com.shamilov.payda.ui.interfaces

import com.shamilov.payda.domain.model.FeeEntity

/**
 * Created by Shamilov on 20.05.2020
 */
interface OnDonationActiveClickListener {
    fun onDonationClick(donation: FeeEntity)
    fun onDonationHelpClick(donation: FeeEntity)
    fun onShareClick()
}