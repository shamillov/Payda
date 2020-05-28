package com.shamilov.payda.ui.interfaces

import com.shamilov.payda.data.model.DonationCompletedData

/**
 * Created by Shamilov on 20.05.2020
 */
interface OnDonationCompletedClickListener {
    fun onDonationClick(donation: DonationCompletedData)
    fun onDonationHelpClick(donation: DonationCompletedData)
    fun onShareClick()
}