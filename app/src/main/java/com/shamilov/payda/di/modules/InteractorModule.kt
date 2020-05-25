package com.shamilov.payda.di.modules

import com.shamilov.payda.domain.executor.SchedulerProvider
import com.shamilov.payda.domain.interactor.GetActiveDonationUsecase
import com.shamilov.payda.domain.repository.DonationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Shamilov on 25.05.2020
 */
@Module
class InteractorModule {

    @Singleton
    @Provides
    fun provideGetActiveDonationUseCase(repository: DonationRepository, scheduler: SchedulerProvider): GetActiveDonationUsecase {
        return GetActiveDonationUsecase(repository, scheduler)
    }
}