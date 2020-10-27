package com.shamilov.payda.data.local.db

import android.content.Context
import androidx.room.Room

/**
 * Created by Shamilov on 27.10.2020
 */
object DatabaseFactory {
    private val LOCK = Any()

    private var database: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return database ?: synchronized(LOCK) {
            Room.databaseBuilder(context, AppDatabase::class.java, "payda.db")
                .fallbackToDestructiveMigration()
                .build().also { database = it }
        }
    }
}