package com.shamilov.payda.di

import com.shamilov.payda.data.mapper.DonationMapper
import com.shamilov.payda.data.remote.ApiServiceFactory
import com.shamilov.payda.data.repository.DonationRepositoryImpl
import com.shamilov.payda.domain.executor.SchedulerProvider
import com.shamilov.payda.domain.executor.SchedulerProviderImpl
import com.shamilov.payda.domain.interactor.GetDonationUseCase
import com.shamilov.payda.domain.repository.DonationRepository
import org.koin.dsl.module

/**
 * Created by Shamilov on 15.08.2020
 */
val dataModule = module {
    single<DonationRepository> { DonationRepositoryImpl(get(), get()) }
}

val mapperModule = module {
    single { DonationMapper() }
}

val interactorModule = module {
    single { GetDonationUseCase(get(), get()) }
}

val networkModule = module {
    single { ApiServiceFactory.createRetrofit(get(), get(), get()) }
    single { ApiServiceFactory.createOkHttpClient() }
    single { ApiServiceFactory.createApiService(get()) }
    single { ApiServiceFactory.createRxJava2CallAdapterFactory() }
    single { ApiServiceFactory.createGsonConverterFactory() }
}

val schedulesModule = module {
    single<SchedulerProvider> { SchedulerProviderImpl() }
}