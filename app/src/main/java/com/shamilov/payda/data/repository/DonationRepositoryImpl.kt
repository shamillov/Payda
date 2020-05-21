package com.shamilov.payda.data.repository

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.shamilov.payda.data.models.Donation
import com.shamilov.payda.data.remote.ApiClient
import com.shamilov.payda.data.remote.api.DonationService
import com.shamilov.payda.domain.repository.DonationRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DonationRepositoryImpl() : DonationRepository {
    override fun getActiveDonation(): Observable<JsonArray> {
        val donationService: DonationService = ApiClient.getInstance()!!

        return donationService
            .getActiveDonation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCompletedDonation(): Observable<Donation> {
        val donationService: DonationService = ApiClient.getInstance()!!

        return donationService
            .getCompletedDonation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}