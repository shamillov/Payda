package com.shamilov.payda.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single

/**
 * Created by Shamilov on 13.10.2020
 */
@Dao
interface FavoriteDonationDao {
    @Query(value = "SELECT * FROM favorite_donation")
    fun getFavoriteDonation(): Single<List<String>>
    fun insertFavoriteDonation(list: List<String>)
}