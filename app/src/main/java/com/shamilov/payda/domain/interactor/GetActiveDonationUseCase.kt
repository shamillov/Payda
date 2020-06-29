package com.shamilov.payda.domain.interactor

import com.shamilov.payda.domain.model.FeeEntity
import com.shamilov.payda.domain.executor.SchedulerProvider
import com.shamilov.payda.domain.repository.DonationRepository
import io.reactivex.Observable

/**
 * Created by Shamilov on 20.05.2020
 */
class GetActiveDonationUseCase(
    private val repository: DonationRepository,
    private val schedulers: SchedulerProvider
) : ObservableUseCase<FeeEntity> {
    override fun execute(): Observable<List<FeeEntity>> {
        return repository.getFee()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }
}