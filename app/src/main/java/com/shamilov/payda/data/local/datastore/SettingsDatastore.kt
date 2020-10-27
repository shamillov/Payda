package com.shamilov.payda.data.local.datastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Shamilov on 09/10/2020
 */
class SettingsDatastore(private val context: Context) {

    companion object {
        const val DATASTORE_NAME = "settings"
        val KEY = preferencesKey<String>("host")
    }

    private val dataStore: DataStore<Preferences> = context.createDataStore(DATASTORE_NAME)

    suspend fun putBaseUrl(url: String) {
        dataStore.edit { preferences ->
            preferences[KEY] = url
        }
    }

    suspend fun resetBaseUrl() {
        dataStore.edit { preferences ->
            preferences[KEY] = ""
        }
    }

    val getHost: Flow<String> = dataStore.data.map { preferences ->
        preferences[KEY] ?: ""
    }

}