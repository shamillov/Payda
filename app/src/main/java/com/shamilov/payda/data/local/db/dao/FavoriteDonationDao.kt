package com.shamilov.payda.data.local.db.dao

import androidx.room.*
import com.shamilov.payda.data.local.db.entity.FavoriteDonationEntity
import com.shamilov.payda.domain.model.DonationEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Shamilov on 13.10.2020
 */
@Dao()
interface FavoriteDonationDao {
    @Query(value = "SELECT * FROM favorite_donation")
    fun getFavoritesDonation(): Single<List<FavoriteDonationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteDonation(donation: FavoriteDonationEntity): Completable

    @Delete
    fun deleteFavoriteDonation(donation: FavoriteDonationEntity): Completable
}