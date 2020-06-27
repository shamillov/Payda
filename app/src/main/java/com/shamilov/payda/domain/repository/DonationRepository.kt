package com.shamilov.payda.domain.repository

import com.shamilov.payda.domain.model.DonationActiveEntity
import com.shamilov.payda.domain.model.DonationCompletedEntity
import com.shamilov.payda.domain.model.FundsEntity
import io.reactivex.Observable

/**
 * Created by Shamilov on 20.05.2020
 */
interface DonationRepository {
    fun getActiveDonation(): Observable<List<DonationActiveEntity>>
    fun getCompletedDonation(): Observable<List<DonationCompletedEntity>>
    fun getFunds(): Observable<List<FundsEntity>>
}