package com.shamilov.payda.di

import androidx.datastore.preferences.createDataStore
import com.shamilov.payda.data.local.datastore.DatastoreKeys.DATASTORE_NAME
import com.shamilov.payda.data.local.datastore.SettingsDatastore
import com.shamilov.payda.data.local.db.DatabaseFactory
import com.shamilov.payda.data.mapper.DonationMapper
import com.shamilov.payda.data.provider.ResourceProvider
import com.shamilov.payda.data.remote.ApiServiceFactory
import com.shamilov.payda.data.repository.DatastoreRepositoryImpl
import com.shamilov.payda.data.repository.RemoteRepositoryImpl
import com.shamilov.payda.data.repository.LocalRepositoryImpl
import com.shamilov.payda.domain.executor.SchedulerProvider
import com.shamilov.payda.domain.executor.SchedulerProviderImpl
import com.shamilov.payda.domain.interactor.DonationInteractor
import com.shamilov.payda.domain.interactor.FundInteractor
import com.shamilov.payda.domain.interactor.ProfileInteractor
import com.shamilov.payda.domain.repository.DatastoreRepository
import com.shamilov.payda.domain.repository.RemoteRepository
import com.shamilov.payda.domain.repository.LocalRepository
import com.shamilov.payda.utils.HostSelectionInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Shamilov on 15.08.2020
 */
val dataModule = module {
    single<RemoteRepository> { RemoteRepositoryImpl(get(), get()) }
    single<LocalRepository> { LocalRepositoryImpl(get()) }
    single<DatastoreRepository> { DatastoreRepositoryImpl(androidContext().createDataStore(DATASTORE_NAME)) }
    single { SettingsDatastore(get()) }
    single { DatabaseFactory.getDatabase(get()).favoriteDao() }
    single { ResourceProvider(androidContext().resources) }
}

val mapperModule = module {
    single { DonationMapper() }
}

val interactorModule = module {
    single { DonationInteractor(get(), get(), get()) }
    single { ProfileInteractor(get(), get(), get()) }
    single { FundInteractor(get()) }
}

val networkModule = module {
    single { ApiServiceFactory.createRetrofit(get(), get(), get()) }
    single { ApiServiceFactory.createOkHttpClient(get(), get()) }
    single { ApiServiceFactory.createApiService(get()) }
    single { ApiServiceFactory.createRxJava2CallAdapterFactory() }
    single { ApiServiceFactory.createGsonConverterFactory() }
    single { ApiServiceFactory.createHttpLoggingInterceptor() }
    single { HostSelectionInterceptor() }
}

val schedulesModule = module {
    single<SchedulerProvider> { SchedulerProviderImpl() }
}