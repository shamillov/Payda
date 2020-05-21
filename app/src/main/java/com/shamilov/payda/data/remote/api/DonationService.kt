package com.shamilov.payda.data.remote.api

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.shamilov.payda.data.models.Donation
import com.shamilov.payda.utils.const.Const
import io.reactivex.Observable
import retrofit2.http.GET

interface DonationService {
    @GET("api/donations/")
    fun getActiveDonation(): Observable<JsonArray>

    @GET(Const.ENDPOINT_DONATION)
    fun getCompletedDonation(): Observable<Donation>

}