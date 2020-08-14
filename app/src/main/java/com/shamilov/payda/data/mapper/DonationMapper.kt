package com.shamilov.payda.data.mapper

import com.shamilov.payda.data.model.DonationData
import com.shamilov.payda.data.model.FundData
import com.shamilov.payda.data.model.ImageData
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.domain.model.FundEntity
import com.shamilov.payda.domain.model.ImageEntity

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationMapper {

    fun mapDonationList(donationList: List<DonationData>): List<DonationEntity> {
        return donationList.map {
            DonationEntity(
                id = it.id,
                fundId = it.fund.id,
                title = it.title,
                description = it.description,
                amount = it.amount,
                region = it.region,
                progress = it.donations,
                images = mapImages(it.images),
                fundLogo = it.fund.logo,
                fundName = it.fund.name
            )
        }
    }

    fun mapFunds(fund: FundData): FundEntity {
        return FundEntity(
            id = fund.id,
            logo = fund.logo,
            name = fund.name
        )
    }

    private fun mapImages(image: List<ImageData>): List<ImageEntity> {
        return image.map {
            ImageEntity(
                id = it.id,
                image = it.image
            )
        }
    }
}