package com.shamilov.payda.domain.interactor

import com.shamilov.payda.domain.executor.SchedulerProvider
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.domain.repository.DonationRepository
import io.reactivex.Observable

/**
 * Created by Shamilov on 20.05.2020
 */
class GetDonationUseCase(
    private val repository: DonationRepository,
    private val schedulers: SchedulerProvider
) : ObservableUseCase<DonationEntity> {
    override fun getDonations(): Observable<List<DonationEntity>> {
        return repository.getDonation()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }
}