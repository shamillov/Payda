package com.shamilov.payda.domain.interactor

import com.shamilov.payda.data.local.db.entity.FavoriteDonationEntity
import com.shamilov.payda.domain.executor.SchedulerProvider
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.domain.repository.DonationRepository
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Shamilov on 20.05.2020
 */
class GetDonationUseCase(
    private val repository: DonationRepository,
    private val schedulers: SchedulerProvider
) {
    fun getDonations(): Single<List<DonationEntity>> {
        return repository.getDonation()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    fun getFavoritesDonation(): Single<List<FavoriteDonationEntity>> {
        return repository.getFavoritesDonation()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }
    
    fun insertFavoriteDonation(donation: FavoriteDonationEntity): Completable {
        return repository.insertFavoriteDonation(donation)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    fun deleteFavoriteDonation(donation: FavoriteDonationEntity): Completable {
        return repository.deleteFavoriteDonation(donation)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }
}