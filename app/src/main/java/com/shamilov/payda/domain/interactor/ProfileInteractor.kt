package com.shamilov.payda.domain.interactor

import com.shamilov.payda.domain.repository.DatastoreRepository
import com.shamilov.payda.domain.repository.LocalRepository
import com.shamilov.payda.domain.repository.RemoteRepository

/**
 * Created by Shamilov on 06.11.2020
 */
class ProfileInteractor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val datastoreRepository: DatastoreRepository
) {
    fun getContributionCount() = datastoreRepository.getContribution
}