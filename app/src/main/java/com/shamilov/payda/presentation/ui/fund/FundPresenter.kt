package com.shamilov.payda.presentation.ui.fund

import com.shamilov.common.base.BasePresenter
import com.shamilov.payda.domain.interactor.FundInteractor
import moxy.InjectViewState
import org.koin.core.inject

/**
 * Created by Shamilov on 29.12.2020
 */
@InjectViewState
class FundPresenter(private val id: Int) : BasePresenter<FundView>() {

    private val interactor: FundInteractor by inject()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadFund(id)
    }

    private fun loadFund(id: Int) {
        launch {
            interactor.getFundById(id)
                .subscribe(viewState::setFund, this::handleError)
        }
    }
}