package com.shamilov.payda.domain.interactor

import com.shamilov.payda.data.model.FundsData
import com.shamilov.payda.domain.executor.SchedulerProvider
import com.shamilov.payda.domain.model.FundsEntity
import com.shamilov.payda.domain.repository.DonationRepository
import io.reactivex.Observable

/**
 * Created by Shamilov on 22.06.2020
 */
class GetFundsUseCase(private val repository: DonationRepository,
                      private val schedulers: SchedulerProvider): ObservableUseCase<FundsEntity> {
    override fun execute(): Observable<List<FundsEntity>> {
        return repository.getFunds()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }
}