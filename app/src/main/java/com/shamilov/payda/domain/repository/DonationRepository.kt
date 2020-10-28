package com.shamilov.payda.domain.repository

import com.shamilov.payda.data.local.db.entity.FavoriteDonationEntity
import com.shamilov.payda.data.model.PaymentResponse
import com.shamilov.payda.domain.model.DonationEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Shamilov on 20.05.2020
 */
interface DonationRepository {
    fun getDonation(): Single<List<DonationEntity>>
    fun payment(id: Int, amount: String, currency: String, paymentToken: String): Single<PaymentResponse>
    fun getFavoritesDonation(): Single<List<FavoriteDonationEntity>>
    fun insertFavoriteDonation(donation: FavoriteDonationEntity): Completable
    fun deleteFavoriteDonation(donation: FavoriteDonationEntity): Completable
//    fun getFunds(): Observable<List<FundsEntity>>
//    fun getFee(): Observable<List<FeeEntity>>
}