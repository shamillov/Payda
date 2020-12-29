package com.shamilov.payda.domain.interactor

import com.shamilov.payda.domain.model.FundEntity
import com.shamilov.payda.domain.repository.RemoteRepository
import com.shamilov.payda.extensions.ioToUi
import io.reactivex.Single

/**
 * Created by Shamilov on 29.12.2020
 */
class FundInteractor(
    private val remoteRepository: RemoteRepository
) {
    fun getFunds(): Single<List<FundEntity>> {
        return remoteRepository.getFunds()
            .ioToUi()
    }

    fun getFundById(id: Int): Single<FundEntity> {
        return remoteRepository.getFundById(id)
            .ioToUi()
    }
}