package com.shamilov.payda.data.remote

import com.shamilov.payda.utils.const.Const
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit? {
            if (instance == null) {
                synchronized(this) {
                    instance = Retrofit.Builder()
                        .baseUrl(Const.BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return instance
        }
    }
}