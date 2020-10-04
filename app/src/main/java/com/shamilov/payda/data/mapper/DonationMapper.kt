package com.shamilov.payda.data.mapper

import com.shamilov.payda.data.model.DonationResponse
import com.shamilov.payda.data.model.FundResponse
import com.shamilov.payda.data.model.ImageResponse
import com.shamilov.payda.data.model.UserResponse
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.domain.model.FundEntity
import com.shamilov.payda.domain.model.ImageEntity
import java.util.*

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationMapper {
    fun mapDonationList(donationList: List<DonationResponse>): List<DonationEntity> {
        return donationList.map {
            DonationEntity(
                id = it.id ?: -1,
                fundId = it.fund.id ?: -1,
                title = it.title ?: "",
                description = it.description ?: "",
                amount = String.format(Locale.CANADA_FRENCH, "%,d", it.amount),
                region = it.region ?: "",
                progress = String.format(Locale.CANADA_FRENCH, "%,d", it.donations),
                images = mapImageList(it.images),
                fundLogo = it.fund.logo ?: "",
                fundName = it.fund.name ?: ""
            )
        }
    }

    fun mapFund(fund: FundResponse): FundEntity {
        return FundEntity(
            id = fund.id ?: -1,
            logo = fund.logo ?: "",
            name = fund.name ?: ""
        )
    }

    private fun mapImageList(image: List<ImageResponse>): List<ImageEntity> {
        return image.map {
            ImageEntity(
                id = it.id ?: -1,
                image = it.image ?: ""
            )
        }
    }

}