package com.shamilov.payda.domain.repository

import com.shamilov.payda.domain.model.DonationEntity
import io.reactivex.Observable

/**
 * Created by Shamilov on 20.05.2020
 */
interface DonationRepository {
    fun getDonation(): Observable<List<DonationEntity>>
//    fun getFunds(): Observable<List<FundsEntity>>
//    fun getFee(): Observable<List<FeeEntity>>
}