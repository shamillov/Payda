package com.shamilov.payda.data.mapper

import com.shamilov.payda.data.model.DonationActiveData
import com.shamilov.payda.domain.model.DonationActiveEntity

class DonationMapper {

    fun map(donation: DonationActiveData): DonationActiveEntity {
        return DonationActiveEntity(
            donation.donationTitle,
            donation.donationDescription,
            donation.donationAmount,
            donation.fundLocation
        )
    }

    fun mapList(donationList: List<DonationActiveData>): List<DonationActiveEntity> {
        return donationList.map {
            DonationActiveEntity(
                title = it.donationTitle,
                description = it.donationDescription,
                amount = it.donationAmount,
                location = it.fundLocation
            )
        }
    }
}