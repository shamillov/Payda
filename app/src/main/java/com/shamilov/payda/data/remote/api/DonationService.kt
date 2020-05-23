package com.shamilov.payda.data.remote.api

import com.shamilov.payda.data.model.DonationActiveData
import com.shamilov.payda.data.model.DonationCompletedData
import com.shamilov.payda.utils.const.Const
import io.reactivex.Observable
import retrofit2.http.GET

interface DonationService {
    @GET(Const.ENDPOINT_DONATION_ACTIVE)
    fun getActiveDonation(): Observable<List<DonationActiveData>>

    @GET(Const.ENDPOINT_DONATION_COMPLETED)
    fun getCompletedDonation(): Observable<List<DonationCompletedData>>

}