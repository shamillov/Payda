package com.shamilov.payda.data.repository

import com.shamilov.payda.data.mapper.DonationMapper
import com.shamilov.payda.data.model.DonationCompletedData
import com.shamilov.payda.data.remote.ApiClient
import com.shamilov.payda.data.remote.api.DonationService
import com.shamilov.payda.domain.model.DonationActiveEntity
import com.shamilov.payda.domain.repository.DonationRepository
import io.reactivex.Observable

class DonationRepositoryImpl(private val mapper: DonationMapper) : DonationRepository {
    override fun getActiveDonation(): Observable<List<DonationActiveEntity>> {
        val donationService: DonationService = ApiClient.getInstance()!!

        return donationService.getActiveDonation()
            .map { mapper.mapList(it) }
    }

    override fun getCompletedDonation(): Observable<List<DonationCompletedData>> {
        val donationService: DonationService = ApiClient.getInstance()!!

        return donationService.getCompletedDonation()
    }
}