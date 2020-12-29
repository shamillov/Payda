package com.shamilov.payda.presentation.ui.funds

import androidx.core.os.bundleOf
import com.shamilov.common.base.BasePresenter
import com.shamilov.payda.R
import com.shamilov.payda.domain.interactor.FundInteractor
import com.shamilov.payda.presentation.ui.fund.FundFragment.Companion.KEY_FUND_ID
import com.shamilov.payda.presentation.ui.funds.viewholder.FundViewHolder
import com.shamilov.payda.presentation.ui.funds.viewholder.OnFundClickListener
import com.xwray.groupie.Group
import moxy.InjectViewState
import org.koin.core.inject

/**
 * Created by Shamilov on 29.12.2020
 */
@InjectViewState
class FundsPresenter : BasePresenter<FundsView>(), OnFundClickListener {

    private val interactor: FundInteractor by inject()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadFunds()
    }

    private fun loadFunds() {
        launch {
            interactor.getFunds()
                .map { funds ->
                    val items = mutableListOf<Group>()
                    items.addAll(funds.map { FundViewHolder(it, this) })

                    return@map items
                }
                .subscribe(viewState::setFunds, this::handleError)
        }
    }

    override fun onFundClick(id: Int) {
        navigate(R.id.action_fundsFragment_to_fundFragment, bundleOf(KEY_FUND_ID to id))
    }
}