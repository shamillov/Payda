package com.shamilov.payda.domain.interactor

import com.shamilov.payda.domain.executor.SchedulerProvider
import com.shamilov.payda.domain.model.DonationActiveEntity
import com.shamilov.payda.domain.repository.DonationRepository
import io.reactivex.Observable

class GetActiveDonationUsecase(private val repository: DonationRepository,
                               private val schedulers: SchedulerProvider): ObservableUseCase<DonationActiveEntity> {
    override fun execute(): Observable<List<DonationActiveEntity>> {
        return repository.getActiveDonation()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

}