package com.shamilov.payda.data.provider

import android.content.res.Resources

/**
 * Created by Shamilov on 28.10.2020
 */
class ResourceProvider(private val resource: Resources) {

    fun getString(id: Int): String {
        return resource.getString(id)
    }

    fun getString(id: Int, vararg args: Any): String {
        return resource.getString(id, args)
    }
}