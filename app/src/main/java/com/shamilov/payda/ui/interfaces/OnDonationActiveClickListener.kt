package com.shamilov.payda.ui.interfaces

import com.shamilov.payda.domain.model.DonationActiveEntity

interface OnDonationActiveClickListener {
    fun onDonationClick(donation: DonationActiveEntity)
    fun onDonationHelpClick(donation: DonationActiveEntity)
}