package com.shamilov.payda.domain.repository

import com.shamilov.payda.data.model.DonationCompletedData
import com.shamilov.payda.domain.model.DonationActiveEntity
import io.reactivex.Observable

interface DonationRepository {
    fun getActiveDonation(): Observable<List<DonationActiveEntity>>
    fun getCompletedDonation(): Observable<List<DonationCompletedData>>
}