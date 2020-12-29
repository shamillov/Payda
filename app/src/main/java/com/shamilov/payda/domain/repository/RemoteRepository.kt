package com.shamilov.payda.domain.repository

import com.shamilov.payda.data.model.response.FundResponse
import com.shamilov.payda.data.model.response.PaymentResponse
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.domain.model.FundEntity
import io.reactivex.Single

/**
 * Created by Shamilov on 20.05.2020
 */
interface RemoteRepository {
    fun getDonation(): Single<List<DonationEntity>>
    fun payment(id: Int, amount: String, currency: String, paymentToken: String): Single<PaymentResponse>
    fun getFunds(): Single<List<FundEntity>>
    fun getFundById(id: Int): Single<FundEntity>
//    fun getFee(): Observable<List<FeeEntity>>
}