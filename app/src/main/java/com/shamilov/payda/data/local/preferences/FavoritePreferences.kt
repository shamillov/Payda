package com.shamilov.payda.data.local.preferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Shamilov on 12.07.2020
 */
class FavoritePreferences(context: Context) {

    companion object {
        private val PREFS_NAME = FavoritePreferences::class.java.simpleName
    }

    private var prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}