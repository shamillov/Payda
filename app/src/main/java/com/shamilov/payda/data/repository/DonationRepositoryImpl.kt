package com.shamilov.payda.data.repository

import com.shamilov.payda.data.mapper.DonationMapper
import com.shamilov.payda.data.remote.api.DonationService
import com.shamilov.payda.domain.model.DonationActiveEntity
import com.shamilov.payda.domain.model.DonationCompletedEntity
import com.shamilov.payda.domain.repository.DonationRepository
import io.reactivex.Observable

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationRepositoryImpl(
    private var api: DonationService,
    private val mapper: DonationMapper
) : DonationRepository {

    override fun getActiveDonation(): Observable<List<DonationActiveEntity>> {
        return api.getActiveDonation()
            .map { mapper.mapList(it) }
    }

    override fun getCompletedDonation(): Observable<List<DonationCompletedEntity>> {
        return api.getCompletedDonation()
            .map { mapper.mapCompletedList(it) }
    }
}