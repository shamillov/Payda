package com.shamilov.payda.data.repository

import com.shamilov.payda.data.local.db.dao.FavoriteDonationDao
import com.shamilov.payda.data.local.db.entity.FavoriteDonationEntity
import com.shamilov.payda.data.mapper.DonationMapper
import com.shamilov.payda.data.model.PaymentRequest
import com.shamilov.payda.data.model.PaymentResponse
import com.shamilov.payda.data.remote.api.DonationService
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.domain.repository.DonationRepository
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationRepositoryImpl(
    private var api: DonationService,
    private val mapper: DonationMapper,
    private val dao: FavoriteDonationDao
) : DonationRepository {

    override fun getDonation(): Single<List<DonationEntity>> {
        return api.getDonation()
            .map { mapper.mapDonationList(it) }
    }

    override fun payment(
        id: Int,
        amount: String,
        currency: String,
        paymentToken: String
    ): Single<PaymentResponse> {
        return api.payment(id, PaymentRequest(amount, currency, paymentToken))
    }

    override fun getFavoritesDonation(): Single<List<FavoriteDonationEntity>> {
        return dao.getFavoritesDonation()
    }

    override fun insertFavoriteDonation(donation: FavoriteDonationEntity): Completable {
        return dao.insertFavoriteDonation(donation)
    }

    override fun deleteFavoriteDonation(donation: FavoriteDonationEntity): Completable {
        return dao.deleteFavoriteDonation(donation)
    }
}