package com.shamilov.payda.ui.interfaces

import com.shamilov.payda.data.model.DonationCompletedData

interface OnDonationCompletedClickListener {
    fun onDonationClick(donation: DonationCompletedData)
    fun onDonationHelpClick(donation: DonationCompletedData)
}