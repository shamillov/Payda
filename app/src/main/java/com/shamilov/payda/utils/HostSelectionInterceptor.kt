package com.shamilov.payda.utils

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Created by Shamilov on 27.10.2020
 */
class HostSelectionInterceptor : Interceptor {

    @Volatile private var host: HttpUrl? = null

    fun setHost(host: String) {
        this.host = host.toHttpUrlOrNull()
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = host?.let {
            val newUrl = chain.request().url.newBuilder()
                .host(it.toUrl().toURI().host)
                .build()

            return@let chain.request().newBuilder()
                .url(newUrl)
                .build()
        }
        return chain.proceed(newRequest!!)
    }
}