package com.shamilov.payda.ui.interfaces

import com.shamilov.payda.domain.model.DonationActiveEntity

/**
 * Created by Shamilov on 20.05.2020
 */
interface OnDonationActiveClickListener {
    fun onDonationClick(donation: DonationActiveEntity)
    fun onDonationHelpClick(donation: DonationActiveEntity)
    fun onShareClick()
}