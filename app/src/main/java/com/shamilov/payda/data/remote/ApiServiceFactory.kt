package com.shamilov.payda.data.remote

import com.shamilov.payda.BuildConfig
import com.shamilov.payda.data.remote.api.DonationService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL: String = BuildConfig.PAYDA_SERVICE_HOST

object ApiServiceFactory {
    fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun createRetrofit(
        client: OkHttpClient,
        rxJavaAdapter: RxJava2CallAdapterFactory,
        gsonAdapter: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(rxJavaAdapter)
            .addConverterFactory(gsonAdapter)
            .baseUrl(BASE_URL)
            .build()
    }

    fun createApiService(retrofit: Retrofit): DonationService {
        return retrofit.create(DonationService::class.java)
    }

    fun createRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    fun createGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}