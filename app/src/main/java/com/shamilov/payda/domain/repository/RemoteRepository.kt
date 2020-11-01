package com.shamilov.payda.domain.repository

import com.shamilov.payda.data.model.PaymentResponse
import com.shamilov.payda.domain.model.DonationEntity
import io.reactivex.Single

/**
 * Created by Shamilov on 20.05.2020
 */
interface RemoteRepository {
    fun getDonation(): Single<List<DonationEntity>>
    fun payment(id: Int, amount: String, currency: String, paymentToken: String): Single<PaymentResponse>
//    fun getFunds(): Observable<List<FundsEntity>>
//    fun getFee(): Observable<List<FeeEntity>>
}