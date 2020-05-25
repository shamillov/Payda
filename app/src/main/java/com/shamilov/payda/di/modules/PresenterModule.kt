package com.shamilov.payda.di.modules

import com.shamilov.payda.domain.interactor.GetActiveDonationUsecase
import com.shamilov.payda.ui.presenters.DonationActivePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Shamilov on 25.05.2020
 */
@Module
class PresenterModule {

    @Provides
    fun providePresenter(interactor: GetActiveDonationUsecase): DonationActivePresenter {
        return DonationActivePresenter(interactor)
    }

}