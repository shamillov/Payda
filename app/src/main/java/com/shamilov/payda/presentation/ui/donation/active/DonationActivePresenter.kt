package com.shamilov.payda.presentation.ui.donation.active

import android.content.Context
import com.shamilov.payda.R
import com.shamilov.payda.domain.interactor.GetDonationUseCase
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.base.BasePresenter
import com.shamilov.payda.presentation.ui.donation.active.viewholder.DonationViewHolder
import com.shamilov.payda.presentation.ui.donation.active.viewholder.HeaderViewHolder
import com.shamilov.payda.presentation.ui.donation.completed.DonationCompletedPresenter
import com.xwray.groupie.Group
import moxy.InjectViewState
import org.koin.core.inject
import ru.yandex.money.android.sdk.*
import java.util.*

/**
 * Created by Shamilov on 20.05.2020
 */
@InjectViewState
class DonationActivePresenter : BasePresenter<DonationActiveView>(), DonationViewHolder.DonationListener {

    private val TAG: String = DonationCompletedPresenter::class.java.simpleName

    private val donationUseCase: GetDonationUseCase by inject()

    fun loadData(hasNetwork: Boolean) {
        if (hasNetwork) {
            loadDonations()
        } else {
            viewState.showNetworkError(true)
        }
    }

    fun refreshData() {
        launch {
            donationUseCase.getDonations()
                .doOnSubscribe { viewState.showSwipeLoading(true) }
                .doOnSuccess {
                    viewState.showSwipeLoading(false)
                    viewState.showNetworkError(false)
                    viewState.showEmptyMessage(false)
                }
                .doOnError { viewState.showSwipeLoading(false) }
                .map { donation ->
                    val items = mutableListOf<Group>()
                    items += HeaderViewHolder()
                    items.addAll(donation.map { DonationViewHolder(it, this) })
                    return@map items
                }
                .subscribe(
                    { donation ->
                        if (donation.isNotEmpty()) {
                            viewState.onUpdate(donation)
                        } else {
                            viewState.showEmptyMessage(true)
                        }
                    },
                    { throwable -> handleError(throwable) })
        }
    }

    private fun loadDonations() {
        launch {
            donationUseCase.getDonations()
                .doOnSubscribe { viewState.showLoading(true) }
                .doOnSuccess { viewState.showLoading(false) }
                .doOnError { viewState.showLoading(false) }
                .map { donation ->
                    val items = mutableListOf<Group>()
                    items += HeaderViewHolder()
                    items.addAll(donation.map { DonationViewHolder(it, this) })
                    return@map items
                }
                .subscribe(
                    { donation ->
                        if (donation.isNotEmpty()) {
                            viewState.onSuccess(donation)
                        } else {
                            viewState.showEmptyMessage(true)
                        }
                    },
                    { throwable -> handleError(throwable) }
                )
        }
    }

    override fun onItemClick(donation: DonationEntity) {
        viewState.openDonation(donation)
    }

    override fun onDonateClick(id: Int, context: Context) {
        val parameter = PaymentParameters(
            amount = Amount(100.toBigDecimal(), Currency.getInstance("RUB")),
            title = "Название товара",
            subtitle = "Описание товара",
            clientApplicationKey = context.getString(R.string.client_application_id),
            shopId = context.getString(R.string.shop_id),
            savePaymentMethod = SavePaymentMethod.ON,
            paymentMethodTypes = setOf(
                PaymentMethodType.BANK_CARD,
                PaymentMethodType.GOOGLE_PAY,
                PaymentMethodType.SBERBANK
            )
        )

        val testParameters = TestParameters(
            true,
            googlePayTestEnvironment = true,
            mockConfiguration = MockConfiguration(
                completeWithError = false,
                paymentAuthPassed = true,
                linkedCardsCount = 5
            )
        )

        val uiParameters = UiParameters(showLogo = false)

        viewState.donate(parameter, uiParameters)
    }

    override fun onShareClick() {
        viewState.shareDonation()
    }

    override fun onFavoriteClick() {

    }

}