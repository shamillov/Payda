package com.shamilov.payda.data.local

import android.content.Context
import android.content.SharedPreferences

class ApplicationPreferences(context: Context) {

    companion object {
        private const val FIRST_FUN = "FIRST_RUN"
        private val PREFS_NAME = ApplicationPreferences::class.java.simpleName
    }

    private val prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    var isFirstRun: Boolean
        get() = prefs.getBoolean(FIRST_FUN, true)
        set(value) = prefs.edit().putBoolean(FIRST_FUN, value).apply()

    fun clear() {
        isFirstRun = true
    }
}