package com.shamilov.payda.domain.interactor

import com.shamilov.payda.data.local.db.entity.FavoriteDonationEntity
import com.shamilov.payda.domain.executor.SchedulerProvider
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.domain.repository.LocalRepository
import com.shamilov.payda.domain.repository.RemoteRepository
import com.shamilov.payda.extensions.ioToUi
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationInteractor(
    private val remoteRepository: RemoteRepository,
    private val schedulers: SchedulerProvider,
    private val localRepository: LocalRepository
) {
    fun getDonations(): Single<List<DonationEntity>> {
        return remoteRepository.getDonation()
            .ioToUi()
    }

    fun getFavoritesDonation(): Single<List<FavoriteDonationEntity>> {
        return localRepository.getFavoritesDonation()
            .ioToUi()
    }
    
    fun insertFavoriteDonation(donation: FavoriteDonationEntity): Completable {
        return localRepository.insertFavoriteDonation(donation)
            .ioToUi()
    }

    fun deleteFavoriteDonation(donation: FavoriteDonationEntity): Completable {
        return localRepository.deleteFavoriteDonation(donation)
            .ioToUi()
    }
}