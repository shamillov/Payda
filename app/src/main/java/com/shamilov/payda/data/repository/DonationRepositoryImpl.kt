package com.shamilov.payda.data.repository

import com.shamilov.payda.data.mapper.DonationMapper
import com.shamilov.payda.data.model.PaymentRequest
import com.shamilov.payda.data.model.PaymentResponse
import com.shamilov.payda.data.remote.api.DonationService
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.domain.repository.DonationRepository
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationRepositoryImpl(
    private var api: DonationService,
    private val mapper: DonationMapper
) : DonationRepository {

    override fun getDonation(): Observable<List<DonationEntity>> {
        return api.getDonation()
            .map { mapper.mapDonationList(it) }
    }

    override fun payment(
        id: Int,
        amount: String,
        currency: String,
        paymentToken: String
    ): Single<PaymentResponse> {
        return api.payment(id, PaymentRequest(amount, currency, paymentToken))
    }
}