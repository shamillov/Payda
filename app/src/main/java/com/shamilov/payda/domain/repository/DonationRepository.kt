package com.shamilov.payda.domain.repository

import com.shamilov.payda.domain.model.DonationActiveEntity
import com.shamilov.payda.domain.model.DonationCompletedEntity
import io.reactivex.Observable

/**
 * Created by Shamilov on 20.05.2020
 */
interface DonationRepository {
    fun getActiveDonation(): Observable<List<DonationActiveEntity>>
    fun getCompletedDonation(): Observable<List<DonationCompletedEntity>>
}