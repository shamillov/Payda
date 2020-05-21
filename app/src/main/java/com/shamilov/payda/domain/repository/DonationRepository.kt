package com.shamilov.payda.domain.repository

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.shamilov.payda.data.models.Donation
import io.reactivex.Observable

interface DonationRepository {
    fun getActiveDonation(): Observable<JsonArray>
    fun getCompletedDonation(): Observable<Donation>
}