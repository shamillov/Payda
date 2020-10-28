package com.shamilov.payda.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Shamilov on 13.10.2020
 */
@Entity(tableName = "favorite_donation")
data class FavoriteDonationEntity(
    @PrimaryKey
    val id: Int
)