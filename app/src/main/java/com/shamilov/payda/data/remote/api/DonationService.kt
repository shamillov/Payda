package com.shamilov.payda.data.remote.api

import com.shamilov.payda.data.model.DonationResponse
import com.shamilov.payda.data.model.FundResponse
import com.shamilov.payda.data.model.PaymentRequest
import com.shamilov.payda.data.model.PaymentResponse
import com.shamilov.payda.utils.Const.ENDPOINT_DONATION_ACTIVE
import com.shamilov.payda.utils.Const.ENDPOINT_FUNDS
import com.shamilov.payda.utils.Const.ENDPOINT_PAYMENT
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Shamilov on 15.08.2020
 */
interface DonationService {
    @GET(ENDPOINT_DONATION_ACTIVE)
    fun getDonation(): Single<List<DonationResponse>>

    @GET(ENDPOINT_FUNDS)
    fun getFunds(): Single<List<FundResponse>>

    @POST(ENDPOINT_PAYMENT)
    fun payment(@Path("id") id: Int, paymentRequest: PaymentRequest): Single<PaymentResponse>
}