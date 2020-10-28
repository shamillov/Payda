package com.shamilov.payda.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shamilov.payda.data.local.db.dao.FavoriteDonationDao
import com.shamilov.payda.data.local.db.entity.FavoriteDonationEntity

/**
 * Created by Shamilov on 27.10.2020
 */
@Database(entities = [FavoriteDonationEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDonationDao
}