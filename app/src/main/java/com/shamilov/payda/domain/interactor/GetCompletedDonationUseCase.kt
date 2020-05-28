package com.shamilov.payda.domain.interactor

import com.shamilov.payda.domain.executor.SchedulerProvider
import com.shamilov.payda.domain.model.DonationCompletedEntity
import com.shamilov.payda.domain.repository.DonationRepository
import io.reactivex.Observable

/**
 * Created by Shamilov on 27.05.2020
 */
class GetCompletedDonationUseCase(
    private val repository: DonationRepository,
    private val schedulers: SchedulerProvider
) : ObservableUseCase<DonationCompletedEntity>{
    override fun execute(): Observable<List<DonationCompletedEntity>> {
        return repository.getCompletedDonation()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }
}