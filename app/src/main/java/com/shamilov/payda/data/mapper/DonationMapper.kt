package com.shamilov.payda.data.mapper

import com.shamilov.payda.data.model.response.DonationResponse
import com.shamilov.payda.data.model.response.FileResponse
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
        return donationList.map { donation ->
            DonationEntity(
                id = donation.id ?: -1,
                fundId = donation.fund.id ?: -1,
                title = donation.title ?: "",
                description = donation.description ?: "",
                amount = String.format(Locale.CANADA_FRENCH, "%,d", donation.amount),
                region = donation.region ?: "",
                progress = String.format(Locale.CANADA_FRENCH, "%,d", donation.donations),
                files = mapImageList(donation.files),
                fundLogo = mapFile(donation.fund.logo),
                active = donation.active ?: true,
                fundName = donation.fund.name ?: ""
            )
        }
    }

    fun mapFund(fund: FundResponse): FundEntity {
        return FundEntity(
            id = fund.id ?: -1,
            logo = mapFile(fund.logo),
            background = mapFile(fund.background),
            name = fund.name ?: "",
            region = fund.region ?: ""
        )
    }

    fun mapFundsList(list: List<FundResponse>): List<FundEntity> {
        return list.map { fund ->
            FundEntity(
                id = fund.id ?: -1,
                logo = mapFile(fund.logo),
                background = mapFile(fund.background),
                name = fund.name ?: "",
                region = fund.region ?: ""
            )
        }
    }

    private fun mapImageList(image: List<FileResponse>): List<FileEntity> {
        return image.map {
            mapFile(it)
        }
    }

    private fun mapFile(file: FileResponse?): FileEntity {
        return FileEntity(
            name = file?.name ?: "",
            size = file?.size ?: -1,
            contentType = file?.contentType ?: ""
        )
    }

}