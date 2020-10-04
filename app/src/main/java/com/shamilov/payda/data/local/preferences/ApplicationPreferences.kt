package com.shamilov.payda.data.local.preferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Shamilov on 12.07.2020
 */
class ApplicationPreferences(context: Context) {

    companion object {
        private const val FIRST_FUR = "FIRST_RUN"
        private val PREFS_NAME = ApplicationPreferences::class.java.simpleName
    }

    private val prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    var isFirstRun: Boolean
        get() = prefs.getBoolean(FIRST_FUR, true)
        set(value) = prefs.edit().putBoolean(FIRST_FUR, value).apply()

    fun clear() {
        isFirstRun = true
    }
}