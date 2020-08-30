package com.shamilov.payda.data.remote.api

import com.shamilov.payda.data.model.DonationData
import com.shamilov.payda.data.model.FundData
import com.shamilov.payda.utils.Const
import io.reactivex.Observable
import retrofit2.http.GET
/**
 * Created by Shamilov on 15.08.2020
 */
interface DonationService {
    @GET(Const.ENDPOINT_DONATION_ACTIVE)
    fun getDonation(): Observable<List<DonationData>>

    @GET(Const.ENDPOINT_FUNDS)
    fun getFunds(): Observable<List<FundData>>
}