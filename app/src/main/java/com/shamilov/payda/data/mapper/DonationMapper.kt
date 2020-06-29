package com.shamilov.payda.data.mapper

import com.shamilov.payda.data.model.DonationActiveData
import com.shamilov.payda.data.model.DonationCompletedData
import com.shamilov.payda.domain.model.FeeEntity
import com.shamilov.payda.data.model.FundsData
import com.shamilov.payda.domain.model.DonationActiveEntity
import com.shamilov.payda.domain.model.DonationCompletedEntity
import com.shamilov.payda.domain.model.FundsEntity

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationMapper {

    fun mapDonationActiveList(donationList: List<DonationActiveData>): List<DonationActiveEntity> {
        return donationList.map {
            DonationActiveEntity(
                fundId = it.fundId,
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
                id = it.id,
                logo = it.logo,
                name = it.name
            )
        }
    }

    fun convertToFee(donation: List<DonationActiveEntity>, funds: List<FundsEntity>): List<FeeEntity> {
        val feeEntity: MutableList<FeeEntity> = ArrayList()

        donation.forEach { 
            val fund = funds.filter { fund -> it.fundId == fund.id }.first()
            feeEntity.add(
                FeeEntity(
                    logo = fund.logo,
                    name = fund.name,
                    location = it.location,
                    images = it.images,
                    progress = it.progress,
                    amount = it.amount,
                    title = it.title,
                    description = it.description
                )
            )
        }

        return feeEntity
    }
}