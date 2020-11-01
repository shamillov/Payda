package com.shamilov.payda.data.repository

import com.shamilov.payda.data.local.db.dao.FavoriteDonationDao
import com.shamilov.payda.data.local.db.entity.FavoriteDonationEntity
import com.shamilov.payda.domain.repository.LocalRepository
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Shamilov on 31.10.2020
 */
class LocalRepositoryImpl(private val dao: FavoriteDonationDao) : LocalRepository {
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