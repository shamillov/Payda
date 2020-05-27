package com.shamilov.payda.di.modules

import com.shamilov.payda.data.mapper.DonationMapper
import com.shamilov.payda.data.remote.api.DonationService
import com.shamilov.payda.data.repository.DonationRepositoryImpl
import com.shamilov.payda.domain.repository.DonationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Shamilov on 25.05.2020
 */
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDonationRepository(
        service: DonationService,
        mapper: DonationMapper
    ): DonationRepository {
        return DonationRepositoryImpl(service, mapper)
    }
}