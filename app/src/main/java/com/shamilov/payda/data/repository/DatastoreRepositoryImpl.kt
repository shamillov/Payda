package com.shamilov.payda.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.shamilov.payda.data.local.datastore.DatastoreKeys.CONTRIBUTION_KEY
import com.shamilov.payda.data.local.datastore.DatastoreKeys.FIRST_LAUNCH_KEY
import com.shamilov.payda.data.local.datastore.DatastoreKeys.HOST_KEY
import com.shamilov.payda.domain.repository.DatastoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

/**
 * Created by Shamilov on 01.11.2020
 */
class DatastoreRepositoryImpl(private val datastore: DataStore<Preferences>) : DatastoreRepository {
    override suspend fun isFirstLaunch(): Boolean {
        var firstLaunch = true
        datastore.data.collect { prefs ->
            firstLaunch = prefs[FIRST_LAUNCH_KEY] ?: true
        }
        if (firstLaunch) {
            datastore.edit { prefs -> prefs[FIRST_LAUNCH_KEY] = false }
        }
        return firstLaunch
    }

    override suspend fun setHost(host: String) {
        datastore.edit { prefs -> prefs[HOST_KEY] = host }
    }

    override suspend fun resetHost() {
        datastore.edit { prefs -> prefs[HOST_KEY] = "" }
    }

    override suspend fun getHost(): Flow<String> {
        return datastore.data.map { prefs -> prefs[HOST_KEY] ?: "" }
    }

    override val getContribution: Flow<Int> = datastore.data.map { it[CONTRIBUTION_KEY] ?: 1222 }
}