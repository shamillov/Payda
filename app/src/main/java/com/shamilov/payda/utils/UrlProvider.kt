package com.shamilov.payda.utils

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/**
 * Created by Shamilov on 07/10/2020
 */
class UrlProvider(private val url: String) : BaseUrl {

    private var httpUrl: HttpUrl? = null

    fun changeUrl(url: String) {
        httpUrl = url.toHttpUrlOrNull()
    }

    override fun url(): HttpUrl? {
        return httpUrl
    }

}

interface BaseUrl {
    fun url(): HttpUrl?
}