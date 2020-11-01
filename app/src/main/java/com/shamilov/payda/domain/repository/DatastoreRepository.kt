package com.shamilov.payda.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Created by Shamilov on 01.11.2020
 */
interface DatastoreRepository {
    suspend fun isFirstLaunch(): Boolean
    suspend fun setHost(host: String)
    suspend fun resetHost()
    suspend fun getHost(): Flow<String>
    val getContribution: Flow<Int>
}