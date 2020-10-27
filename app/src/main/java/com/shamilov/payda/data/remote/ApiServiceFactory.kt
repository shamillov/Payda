package com.shamilov.payda.data.remote

import com.shamilov.payda.BuildConfig
import com.shamilov.payda.data.remote.api.DonationService
import com.shamilov.payda.utils.HostSelectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Shamilov on 15.08.2020
 */
const val BASE_URL: String = BuildConfig.PAYDA_SERVICE_HOST

object ApiServiceFactory {
    fun createOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        hostSelectionInterceptor: HostSelectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(hostSelectionInterceptor)
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

    fun createHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}