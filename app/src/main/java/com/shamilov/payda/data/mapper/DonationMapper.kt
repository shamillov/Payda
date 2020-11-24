package com.shamilov.payda.data.mapper

import com.shamilov.payda.data.model.response.DonationResponse
import com.shamilov.payda.data.model.response.FileResponce
import com.shamilov.payda.data.model.response.FundResponse
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.domain.model.FileEntity
import com.shamilov.payda.domain.model.FundEntity
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
                files = mapImageList(it.files),

                fundLogo = mapFile(it.fund.logo),
                fundName = it.fund.name ?: ""
            )
        }
    }

    fun mapFund(fund: FundResponse): FundEntity {
        return FundEntity(
            id = fund.id ?: -1,
            logo = mapFile(fund.logo),
            background = mapFile(fund.background),
            name = fund.name ?: ""
        )
    }

    private fun mapImageList(image: List<FileResponce>): List<FileEntity> {
        return image.map {
            mapFile(it)
        }
    }

    private fun mapFile(file: FileResponce?): FileEntity {
        return FileEntity(
            name = file?.name ?: "",
            size = file?.size ?: -1,
            contentType = file?.contentType ?: ""
        )
    }

}