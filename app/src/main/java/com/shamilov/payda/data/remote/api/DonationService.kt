package com.shamilov.payda.data.remote.api

import com.shamilov.payda.data.model.response.DonationResponse
import com.shamilov.payda.data.model.response.FundResponse
import com.shamilov.payda.data.model.request.PaymentRequest
import com.shamilov.payda.data.model.response.PaymentResponse
import com.shamilov.payda.utils.Const.ENDPOINT_DONATION_ACTIVE
import com.shamilov.payda.utils.Const.ENDPOINT_FUNDS
import com.shamilov.payda.utils.Const.ENDPOINT_PAYMENT
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by Shamilov on 15.08.2020
 */
interface DonationService {
    @GET("api/fees")
    fun getDonations(): Single<List<DonationResponse>>

    @GET("api/funds")
    fun getFunds(): Single<List<FundResponse>>

    @GET("api/funds/{id}")
    fun getFundById(@Path("id") id: Int): Single<FundResponse>

    @POST("api/fees/{id}/donate/init")
    fun payment(@Path("id") id: Int, paymentRequest: PaymentRequest): Single<PaymentResponse>

//    @POST(ENDPOINT_DONATION_ATTACH)
//    @Multipart
//    fun attachFile(
//        @Path("id") id: Int,
//        @Part("files") files: List<MultipartBody.Part>
//    ): Single<DonationResponse>
}