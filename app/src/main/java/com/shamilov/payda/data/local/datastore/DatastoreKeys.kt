package com.shamilov.payda.data.local.datastore

import androidx.datastore.preferences.preferencesKey

/**
 * Created by Shamilov on 01.11.2020
 */
object DatastoreKeys {
    const val DATASTORE_NAME = "PAYDA_DATASTORE"
    val FIRST_LAUNCH_KEY = preferencesKey<Boolean>("FIRST_LAUNCH_KEY")
    val CONTRIBUTION_KEY= preferencesKey<Int>("CONTRIBUTION_KEY")
    val HOST_KEY = preferencesKey<String>("HOST_KEY")
}