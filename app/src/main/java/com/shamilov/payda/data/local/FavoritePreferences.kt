package com.shamilov.payda.data.local

import android.content.Context
import android.content.SharedPreferences

class FavoritePreferences(context: Context) {

    companion object {
        private val PREFS_NAME = FavoritePreferences::class.java.simpleName
    }

    private var prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}