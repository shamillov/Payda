package com.shamilov.payda.data.mapper

import com.shamilov.payda.data.model.DonationActiveData
import com.shamilov.payda.data.model.DonationCompletedData
import com.shamilov.payda.data.model.FundsData
import com.shamilov.payda.domain.model.DonationActiveEntity
import com.shamilov.payda.domain.model.DonationCompletedEntity
import com.shamilov.payda.domain.model.FundsEntity

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationMapper {
    fun mapDonationActive(donation: DonationActiveData): DonationActiveEntity {
        return DonationActiveEntity(
            donation.donationTitle,
            donation.donationDescription,
            donation.donationAmount,
            donation.fundLocation,
            donation.donationProgress,
            donation.images
        )
    }

    fun mapDonationActiveList(donationList: List<DonationActiveData>): List<DonationActiveEntity> {
        return donationList.map {
            DonationActiveEntity(
                title = it.donationTitle,
                description = it.donationDescription,
                amount = it.donationAmount,
                location = it.fundLocation,
                progress = it.donationProgress,
                images = it.images
            )
        }
    }

    fun mapDonationCompletedList(donationList: List<DonationCompletedData>): List<DonationCompletedEntity> {
        return donationList.map {
            DonationCompletedEntity()
        }
    }

    fun mapFundsList(fundsList: List<FundsData>): List<FundsEntity> {
        return fundsList.map {
            FundsEntity(
                fundLogo = it.logo,
                funName = it.name,
                fundLocation = it.region)
        }
    }
}