package com.shamilov.payda.domain.repository

import com.shamilov.payda.data.local.db.entity.FavoriteDonationEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Shamilov on 31.10.2020
 */
interface LocalRepository {
    fun getFavoritesDonation(): Single<List<FavoriteDonationEntity>>
    fun insertFavoriteDonation(donation: FavoriteDonationEntity): Completable
    fun deleteFavoriteDonation(donation: FavoriteDonationEntity): Completable
}